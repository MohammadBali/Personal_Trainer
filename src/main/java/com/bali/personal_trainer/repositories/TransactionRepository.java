package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.Entities.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id = :userId AND transaction.id = :transactionId")
    Optional<Transaction> findByUserId(@Param("userId") int userId, @Param("transactionId") int transactionId);

    Optional<List<Transaction>> findByTotalPrice(double totalPrice);

    Optional<List<Transaction>> findByTotalPriceLessThan(double totalPrice);

    Optional<List<Transaction>> findByTotalPriceLessThanEqual(double totalPrice);

    Optional<List<Transaction>> findByTotalPriceGreaterThan(double totalPrice);

    Optional<List<Transaction>> findByTotalPriceGreaterThanEqual(double totalPrice);

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id = :userId")
    Optional<List<Transaction>> findAllByUserId(@Param("userId") int userId);


    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id=:userId AND transaction.totalPrice=:totalPrice")
    Optional<List<Transaction>> findAllByTotalPriceAndUserId(@Param("totalPrice") double totalPrice, @Param("userId") int userId);

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id=:userId AND transaction.totalPrice<:totalPrice")
    Optional<List<Transaction>> findAllByTotalPriceLessThanAndUserId(@Param("totalPrice") double totalPrice, @Param("userId") int userId);

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id=:userId AND transaction.totalPrice<=:totalPrice")
    Optional<List<Transaction>> findAllByTotalPriceLessThanEqualAndUserId(@Param("totalPrice") double totalPrice, @Param("userId") int userId);


    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id=:userId AND transaction.totalPrice>:totalPrice")
    Optional<List<Transaction>> findAllByTotalPriceGreaterThanAndUserId(@Param("totalPrice") double totalPrice, @Param("userId") int userId);

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId.id=:userId AND transaction.totalPrice>=:totalPrice")
    Optional<List<Transaction>> findAllByTotalPriceGreaterEqualThanAndUserId(@Param("totalPrice") double totalPrice, @Param("userId") int userId);

    @Query("DELETE FROM Transaction transaction WHERE transaction.id=:id")
    @Transactional @Modifying
    void deleteById(@Param("id") int id);

}
