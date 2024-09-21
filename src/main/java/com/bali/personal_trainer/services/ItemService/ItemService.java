package com.bali.personal_trainer.services.ItemService;

import com.bali.personal_trainer.models.DTO.ItemDTO;
import com.bali.personal_trainer.models.Entities.Item;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ItemService
{
    /**
     * Add a new Item
     * @param item Item to be inserted
     * @throws IllegalArgumentException If transaction was null
     * @throws OptimisticLockingFailureException otherwise
     * @return Item Object
     * **/
    Item addItem(Item item);

    /**
     * Find an Item By ID
     * @param id Item's ID
     * @throws NoSuchElementException if not found
     * @throws IllegalAccessException If elements weren't accessible
     * @return ItemDTO Object Type
     * **/
    ItemDTO findById(int id) throws IllegalAccessException;

    /**
     * Find an item by Name
     * @param name Item's Name
     * @throws NoSuchElementException if not found
     * @throws IllegalAccessException If elements weren't accessible
     * @return ItemDTO Object Type
     * **/
    ItemDTO findByName(String name) throws IllegalAccessException;

    /**
     * Find Items by Category ID
     * @param id Category's ID
     * @throws NoSuchElementException if not found
     * @throws IllegalAccessException If elements weren't accessible
     * @return Collection of ItemDTO Type
     * **/
    Collection<ItemDTO> findByCategoryId(int id) throws IllegalAccessException;

    /**
     * Find Items by Type
     * @param unitType Type's ID
     * @throws NoSuchElementException if not found
     * @throws IllegalAccessException If elements weren't accessible
     * @return Collection of ItemDTO Type
     * **/
    Collection<ItemDTO> findByUnitType(int unitType) throws IllegalAccessException;

    /**
     * Find Items by Exact Price
     * @param price Exact Item Price
     * @throws NoSuchElementException if not found
     * @throws IllegalAccessException If elements weren't accessible
     * @return Collection of ItemDTO Type
     * **/
    Collection<ItemDTO> findByPrice(double price) throws IllegalAccessException;

    /**
     * Find All Items in Database
     * @return Collection of ItemDTO Type
     * **/
    Collection<ItemDTO> findAll() throws IllegalAccessException;
}
