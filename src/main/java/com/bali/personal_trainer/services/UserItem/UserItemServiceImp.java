package com.bali.personal_trainer.services.UserItem;

import com.bali.personal_trainer.models.ManyToMany.UserItem;
import com.bali.personal_trainer.repositories.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Named
public class UserItemServiceImp implements UserItemService
{
    @Autowired
    private UserItemRepository userItemRepository;

    @Override
    public void add(int userId, int itemId, int limit) {
        userItemRepository.add(userId, itemId, limit);
    }

    @Override
    public UserItem findById(int id) {
        return userItemRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Couldn't find userItem with such ID" + id));
    }

    @Override
    public Collection<UserItem> findByUserId(int userId) {
        return userItemRepository.findByUserId(userId).orElseThrow(()->new NoSuchElementException("Couldn't find userItems with such userId" + userId));
    }

    @Override
    public Collection<UserItem> findByItemId(int itemId) {
        return userItemRepository.findByItemId(itemId).orElseThrow(()-> new NoSuchElementException("Couldn't find userItems with such itemID" + itemId));
    }

    @Override
    public UserItem findByUserIdAndItemId(int userId, int itemId) {
        return userItemRepository.findByUserIdAndItemId(userId,itemId).orElseThrow(()-> new NoSuchElementException("Couldn't find userItems with such userId" + userId + " and itemID" + itemId));
    }

    @Override
    public Collection<UserItem> findAll() {
        return userItemRepository.findAll();
    }


}
