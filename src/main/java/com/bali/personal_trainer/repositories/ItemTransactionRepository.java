package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.Entities.Transaction;
import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface ItemTransactionRepository extends JpaRepository<ItemTransaction, Integer>
{

    @Query("SELECT i FROM ItemTransaction i WHERE i.transaction.id = :transactionId")
    Optional<Collection<ItemTransaction>> findByTransactionId(@Param("transactionId") int transactionId);

    @Query("SELECT i FROM ItemTransaction i WHERE i.userItem.user.id = :userId")
    Optional<Collection<ItemTransaction>> findByUserId(int userId);

    @Query("SELECT i FROM ItemTransaction i WHERE i.userItem.id = :userItemId")
    Optional<Collection<ItemTransaction>> findByUserItemId (int userItemId);

    @Query("SELECT it FROM ItemTransaction it " +
            "JOIN it.transaction t ON t.id = it.transaction.id " +
            "WHERE t.userId.id = :userId " +
            "AND DATE(t.createdAt) >= :startOfMonth AND DATE(t.createdAt) <= :endOfMonth " +
            "AND t.totalPrice = (SELECT MAX(t2.totalPrice) FROM Transaction t2 WHERE t2.userId.id = :userId AND DATE(t2.createdAt) >= :startOfMonth AND DATE(t2.createdAt) <= :endOfMonth) " +
            "ORDER BY it.quantity DESC")
    Optional<Collection<ItemTransaction>> findMostBoughtItemInHighestTransactionThisMonth(@Param("userId") int userId, @Param("startOfMonth") LocalDate startOfMonth, @Param("endOfMonth") LocalDate endOfMonth);

    @Query("SELECT it FROM ItemTransaction it " +
            "JOIN  it.transaction "+
            "WHERE it.transaction.userId.id = :userId " +
            "AND DATE(it.transaction.createdAt) = :date " +
            "ORDER BY it.totalUnitPrice DESC")
    Optional<Collection<ItemTransaction>> findItemsOfDate(@Param("userId") int userId, @Param("date") LocalDate date);

    @Query("SELECT it FROM ItemTransaction it " +
            "JOIN it.transaction t ON it.transaction.id = t.id " +
            "JOIN it.userItem ut ON it.userItem.id = ut.id " +
            "JOIN ut.item item ON ut.item.id = item.id " +
            "JOIN item.categoryId c ON item.categoryId.id = c.id " +
            "WHERE c.id =:category AND t.userId.id =:userId " +
            "ORDER BY it.date DESC")
    Optional<Collection<ItemTransaction>> searchByCategory(@Param("userId") int userId, @Param("category") int category);
}
