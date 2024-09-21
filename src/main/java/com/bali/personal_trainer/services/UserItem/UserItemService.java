package com.bali.personal_trainer.services.UserItem;

import com.bali.personal_trainer.models.ManyToMany.UserItem;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

public interface UserItemService
{
    void add(int userId, int itemId, int limit);

    Collection<UserItem> findByUserId(int userId);

    Collection<UserItem> findByItemId(int itemId);

    UserItem findByUserIdAndItemId(int userId, int itemId);

    Collection<UserItem> findAll();

    UserItem findById(int id);
}
