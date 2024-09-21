package com.bali.personal_trainer.services.ItemService;

import com.bali.personal_trainer.models.DTO.ItemDTO;
import com.bali.personal_trainer.models.Entities.Item;

import java.util.Collection;
import java.util.Optional;

public interface ItemService
{
    Object addItem(Item item);

    ItemDTO findById(int id) throws IllegalAccessException;

    ItemDTO findByName(String name) throws IllegalAccessException;

    Collection<ItemDTO> findByCategoryId(int id) throws IllegalAccessException;

    Collection<ItemDTO> findByUnitType(int unitType) throws IllegalAccessException;

    Collection<ItemDTO> findByPrice(double price) throws IllegalAccessException;

    Collection<ItemDTO> findAll() throws IllegalAccessException;
}
