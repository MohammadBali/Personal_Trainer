package com.bali.personal_trainer.services.TransactionService;

import com.bali.personal_trainer.components.Components;
import com.bali.personal_trainer.models.DTO.TransactionDTO;
import com.bali.personal_trainer.models.Entities.Transaction;
import com.bali.personal_trainer.models.Entities.User;
import com.bali.personal_trainer.repositories.TransactionRepository;
import com.bali.personal_trainer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Named
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Transaction createTransaction(Transaction transaction)
    {

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction createTransactionFromDTO(TransactionDTO transaction)
    {
        // Find the User by ID
        User user = userRepository.findById(transaction.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + transaction.getUserId()));

        Transaction t = new Transaction();
        t.setUserId(user);
        t.setTotalPrice(transaction.getTotalPrice());
        t.setRecurring(transaction.isRecurring());
        t.setRecurrenceInterval(transaction.getRecurrenceInterval());
        t.setNextOccurrence(transaction.getNextOccurrence());

        return transactionRepository.save(t);
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
