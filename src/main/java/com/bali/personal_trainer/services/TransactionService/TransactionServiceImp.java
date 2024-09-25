package com.bali.personal_trainer.services.TransactionService;

import com.bali.personal_trainer.components.Components;
import com.bali.personal_trainer.components.Enums.PriceType;
import com.bali.personal_trainer.models.DTO.UserItemTransactionDTO;
import com.bali.personal_trainer.models.DTO.TransactionDTO;
import com.bali.personal_trainer.models.Entities.Transaction;
import com.bali.personal_trainer.models.Entities.User;
import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import com.bali.personal_trainer.models.ManyToMany.UserItem;
import com.bali.personal_trainer.repositories.ItemTransactionRepository;
import com.bali.personal_trainer.repositories.TransactionRepository;
import com.bali.personal_trainer.services.ItemTransactionService.ItemTransactionService;
import com.bali.personal_trainer.services.UserItem.UserItemService;
import com.bali.personal_trainer.services.UserService.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.*;

@Named
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserItemService userItemService;

    @Autowired
    private ItemTransactionService itemTransactionService;
    @Autowired
    private ItemTransactionRepository itemTransactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction)
    {

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(rollbackOn = {Throwable.class})
    public Transaction createTransactionFromDTO(TransactionDTO transaction)
    {
        // Find the User by ID
        User user = userService.getUser(transaction.getUserId());

        if(transaction.getItemTransactions().isEmpty())
        {
            throw new RuntimeException("Transaction must have at least one item");
        }

        // 1. Create Transaction
        Transaction t = new Transaction(user, transaction.getTotalPrice(), transaction.isRecurring() ,transaction.getRecurrenceInterval(), transaction.getNextOccurrence());
        transactionRepository.save(t);

        // 2. Create UserItems

        ArrayList<UserItem> userItemsFromDB = new ArrayList<>();

        for (int i = 0; i < transaction.getItemTransactions().size(); i++)
        {
            UserItemTransactionDTO item = transaction.getItemTransactions().get(i);
            userItemService.add(user.getId(), item.getItemID(), item.getLimit());
            userItemsFromDB.add(userItemService.findByUserIdAndItemId(user.getId(), item.getItemID()));

            // 3. Create ItemTransactions
            itemTransactionService.add(new ItemTransaction(t, userItemsFromDB.get(i), item.getQuantity(), new Date()));
        }

        return transactionRepository.findById(t.getId()).orElseThrow(()-> new RuntimeException("Unexpected error while getting transaction with id: " + t.getId() + " after updating..."));

    }

    @Override
    public Transaction findById(int id) {
        return transactionRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No Such transaction with such ID" + id));
    }

    @Override
    public Transaction updateTransaction(int userId, Transaction transaction)
    {
        Transaction transactionFromDB = transactionRepository.findByUserId(userId, transaction.getId())
                .orElseThrow(()-> new NoSuchElementException("Transaction with ID " + transaction.getId() + " Doesn't belong to this user"));

        Transaction transactionToBeUpdated = Components.copyNonNullElements(transactionFromDB, transaction);

        assert transactionToBeUpdated != null;
        return transactionRepository.save(transactionToBeUpdated);
    }

    @Override @Transactional
    public String deleteTransaction(int id)
    {
        transactionRepository.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public List<Transaction> findByTotalPrice(double totalPrice, PriceType type)
    {
        switch (type)
        {
            case less ->
            {
                return transactionRepository.findByTotalPriceLessThan(totalPrice)
                        .orElseThrow(()-> new RuntimeException("Could not find transaction with such totalPrice" + totalPrice));
            }

            case lessThanEqual ->
            {
                return transactionRepository.findByTotalPriceLessThanEqual(totalPrice)
                        .orElseThrow(()->new NoSuchElementException("Couldn't find transaction with such totalPrice" + totalPrice));
            }

            case equals ->
            {
                return transactionRepository.findByTotalPrice(totalPrice)
                        .orElseThrow(()-> new RuntimeException("Could not find transaction with such totalPrice" + totalPrice));
            }

            case greater ->
            {
                return transactionRepository.findByTotalPriceGreaterThan(totalPrice)
                        .orElseThrow(()-> new RuntimeException("Could not find transaction with such totalPrice" + totalPrice));
            }

            case greaterThanEqual ->
            {
                return transactionRepository.findByTotalPriceGreaterThanEqual(totalPrice)
                        .orElseThrow(()-> new RuntimeException("Could not find transaction with such totalPrice" + totalPrice));
            }

            default -> throw new RuntimeException("Unexpected Type value: " + type);
        }

    }

    @Override
    public Collection<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Collection<Transaction> findByUserId(int id) {
        return transactionRepository.findAllByUserId(id)
                .orElseThrow(()-> new RuntimeException("Could not find transaction with such userID" + id));
    }

    @Override
    public List<Transaction> findByTotalPriceAndUserId(double totalPrice, int userId, PriceType type)
    {
        switch (type)
        {
            case less ->
            {
                return transactionRepository.findAllByTotalPriceLessThanAndUserId(totalPrice,userId)
                    .orElseThrow(()->new NoSuchElementException("Couldn't find transaction with such totalPrice" + totalPrice + " and userID" + userId));
            }

            case lessThanEqual ->
            {
                return transactionRepository.findAllByTotalPriceLessThanEqualAndUserId(totalPrice,userId)
                        .orElseThrow(()->new NoSuchElementException("Couldn't find transaction with such totalPrice" + totalPrice + " and userID" + userId));
            }

            case equals ->
            {
                return transactionRepository.findAllByTotalPriceAndUserId(totalPrice,userId)
                        .orElseThrow(()->new NoSuchElementException("Couldn't find transaction with such totalPrice" + totalPrice + " and userID" + userId));
            }

            case greater ->
            {
                return transactionRepository.findAllByTotalPriceGreaterThanAndUserId(totalPrice,userId)
                        .orElseThrow(()->new NoSuchElementException("Couldn't find transaction with such totalPrice" + totalPrice + " and userID" + userId));
            }

            case greaterThanEqual ->
            {
                return transactionRepository.findAllByTotalPriceGreaterEqualThanAndUserId(totalPrice,userId)
                        .orElseThrow(()->new NoSuchElementException("Couldn't find transaction with such totalPrice" + totalPrice + " and userID" + userId));
            }

            default -> throw new RuntimeException("Unexpected Type value: " + type);
        }
    }

}
