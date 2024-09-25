package com.bali.personal_trainer.services.ItemTransactionService;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ItemTransactionService
{
    ItemTransaction add(ItemTransaction itemTransaction);

    ItemTransaction findById(int id);

    Collection<ItemTransaction> findByTransactionId(int transactionId);

    Collection<ItemTransaction> findByUserId(int userId);

    Collection<ItemTransaction> findByUserItemId (int userItemId);

    Collection<ItemTransaction> findAll();

    Collection<ItemTransaction> findMostBoughtItemInHighestTransactionThisMonth(int userId, LocalDate startOfMonth, LocalDate endOfMonth);

    /**
     * Returns The Items Bought for this user at this Date
     * @param userId User ID
     * @param date Date ex: 2024-09-23
     * @throws NoSuchElementException If Not Found
     * @return Collection<ItemTransaction>
     * **/
    Collection<ItemTransaction> findItemsOfDate(int userId, LocalDate date);

}
