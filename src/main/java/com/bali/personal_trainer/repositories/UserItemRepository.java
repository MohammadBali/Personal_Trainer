package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.ManyToMany.UserItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface UserItemRepository extends JpaRepository<UserItem,Integer>
{
    @Transactional @Modifying
    @Query(value =
            "INSERT INTO user_item (userID, itemID, `limit`, updatedAt)" +
            "VALUES (:userId, :itemId, :limit, CURRENT_DATE())" +
            "ON DUPLICATE KEY UPDATE `limit` = :limit, updatedAt = CURRENT_DATE()", nativeQuery = true)
    void add(@Param("userId") int userId, @Param("itemId") int itemId, @Param("limit") int limit);

    @Query("SELECT userItem FROM UserItem userItem WHERE userItem.user.id = :userId")
    Optional<Collection<UserItem>> findByUserId(@Param("userId") int userId);

    @Query("SELECT userItem FROM UserItem userItem WHERE userItem.item.id = :itemId")
    Optional<Collection<UserItem>> findByItemId(@Param("itemId") int itemId);


    @Query("SELECT userItem FROM UserItem userItem WHERE userItem.item.id = :itemId AND userItem.user.id = :userId")
    Optional<UserItem> findByUserIdAndItemId(@Param("userId") int userId, @Param("itemId") int itemId);

}
