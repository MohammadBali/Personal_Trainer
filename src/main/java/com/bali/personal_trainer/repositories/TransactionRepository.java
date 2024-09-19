package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.Entities.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id = :userId AND transaction.id = :transactionId")
    Optional<Transaction> findByUserId(@Param("userId") int userId, @Param("transactionId") int transactionId);

    Optional<List<Transaction>> findByTotalPrice(double totalPrice);

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id = :userId")
    Optional<List<Transaction>> findAllByUserId(@Param("userId") int userId);


    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id=:userId AND transaction.totalPrice=:totalPrice")
    Optional<List<Transaction>> findAllByTotalPriceAndUserId(double totalPrice, int userId);

    @Query("DELETE FROM Transaction transaction WHERE transaction.id=:id")
    @Transactional @Modifying
    void deleteById(int id);
}
