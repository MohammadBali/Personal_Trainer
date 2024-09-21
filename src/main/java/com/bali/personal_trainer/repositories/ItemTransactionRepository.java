package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
