package com.bali.personal_trainer.services.TransactionService;

import com.bali.personal_trainer.components.Enums.PriceType;
import com.bali.personal_trainer.models.DTO.TransactionDTO;
import com.bali.personal_trainer.models.Entities.Transaction;
import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import jakarta.transaction.Transactional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface TransactionService
{
    Transaction createTransaction(Transaction transaction);


    /**
     * Create New Transaction
     * @param transaction Pass the Transaction to be saved
     * @return Returns the added Transaction
     * @throws IllegalArgumentException If transaction was null
     * @throws OptimisticLockingFailureException otherwise
     * **/
    @Transactional
    Transaction createTransactionFromDTO(TransactionDTO transaction);

    /**
     * Finds Transaction By ID
     * @param id Transaction ID
     * @return Transaction
     * @throws NoSuchElementException if element was not found
     * **/
    Transaction findById(int id);

    /**
     * Update Transaction
     * @param userId The Owner of that transaction
     * @param transaction The Transaction to be updated
     * @return Updated Transaction
     * @throws NoSuchElementException if Transaction was not found
     * **/
    Transaction updateTransaction(int userId, Transaction transaction);

    /**
     * Delete a Transaction by its ID
     * @param id Transaction ID
     * @return String of success
     * @throws IllegalArgumentException if ID was null
     * **/
    String deleteTransaction(int id);

    /**
     * Find Transactions by TotalPrice Only
     * @param totalPrice The price
     * @param type Equal, Less, Greater, etc...
     * @return List of Transactions
     * @throws RuntimeException if some error
     * **/
    List<Transaction> findByTotalPrice(double totalPrice, PriceType type);

    /**
     * Find Transactions of a user by total price
     * @param userId UserID
     * @param totalPrice TotalPrice to search within
     * @param type Equal, Less, Greater, etc...
     * @return List<Transaction>
     * @throws NoSuchElementException If some error
     * **/
    List<Transaction> findByTotalPriceAndUserId(double totalPrice, int userId, PriceType type);

    /**
     * Find All Transactions
     * @return List of Transactions
     * **/
    Collection<Transaction> findAll();

    /**
     * Find All Transactions of a user
     * @param id userId
     * @return List of Transaction
     * @throws RuntimeException if not found
     * **/
    Collection<Transaction> findByUserId(int id);


}
