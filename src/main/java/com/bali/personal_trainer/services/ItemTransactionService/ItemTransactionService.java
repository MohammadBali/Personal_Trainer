package com.bali.personal_trainer.services.ItemTransactionService;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;

import java.time.LocalDate;
import java.util.Collection;
import java.util.NoSuchElementException;

public interface ItemTransactionService
{
    ItemTransaction add(ItemTransaction itemTransaction);

    ItemTransaction findById(int id);

    Collection<ItemTransaction> findByTransactionId(int transactionId);

    Collection<ItemTransaction> findByUserId(int userId);

    Collection<ItemTransaction> findByUserItemId (int userItemId);

    Collection<ItemTransaction> findAll();

    /**
     * Returns The Items Bought for this user at this Date
     * @param userId User ID
     * @param date Date ex: 2024-09-23, Nullable => Today's Date
     * @throws NoSuchElementException If Not Found
     * @return Collection<ItemTransaction>
     * **/
    Collection<ItemTransaction> findItemsOfDate(int userId, LocalDate date);

    /**
     * Finds The Most Bought Item for a User This Month
     * @param userId User ID
     * @throws NoSuchElementException if Not Found
     * @return ItemTransaction Object
     * **/
    ItemTransaction findMostBoughtItemThisMonth(int userId);

    /**
     * Searches Items Bought by a user in the specified category
     * @param userId The UserID
     * @param category Category's ID
     * @throws NoSuchElementException if not Found
     * @return Collection<ItemTransaction> Object
     * **/
    Collection<ItemTransaction> searchByCategory(int userId, int category);
}
