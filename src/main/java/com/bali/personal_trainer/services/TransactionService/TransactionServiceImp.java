package com.bali.personal_trainer.services.TransactionService;

import com.bali.personal_trainer.components.Components;
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
import io.micrometer.core.instrument.config.validate.ValidationException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

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

    @Override
    public Transaction createTransaction(Transaction transaction)
    {

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(rollbackOn = {Throwable.class})
    public Transaction createTransactionFromDTO(TransactionDTO transaction)
    {
        try
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
        catch (Exception e)
        {
            return null;
        }
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

    @Override
    public String deleteTransaction(int id)
    {
        transactionRepository.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public List<Transaction> findByTotalPrice(double totalPrice) {
        return transactionRepository.findByTotalPrice(totalPrice)
                .orElseThrow(()-> new RuntimeException("Could not find transaction with such totalPrice" + totalPrice));
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
    public List<Transaction> findByTotalPriceAndUserId(double totalPrice, int userId) {
        return transactionRepository.findAllByTotalPriceAndUserId(totalPrice,userId)
                .orElseThrow(()->new NoSuchElementException("Couldn't find transaction with such totalPrice" + totalPrice + " and userID" + userId));
    }
}
