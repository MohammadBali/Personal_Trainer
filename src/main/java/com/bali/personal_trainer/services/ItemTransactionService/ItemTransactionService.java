package com.bali.personal_trainer.services.ItemTransactionService;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;

import java.util.Collection;

public interface ItemTransactionService
{
    ItemTransaction add(ItemTransaction itemTransaction);

    ItemTransaction findById(int id);

    Collection<ItemTransaction> findByTransactionId(int transactionId);

    Collection<ItemTransaction> findByUserId(int userId);

    Collection<ItemTransaction> findByUserItemId (int userItemId);

    Collection<ItemTransaction> findAll();
}
