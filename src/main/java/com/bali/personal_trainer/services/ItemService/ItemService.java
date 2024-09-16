package com.bali.personal_trainer.services.ItemService;

import com.bali.personal_trainer.models.Entities.Item;

import java.util.Collection;
import java.util.Optional;

public interface ItemService
{
    Object addItem(Item item);

    Item findById(int id);

    Item findByName(String name);

    Collection<Item> findByCategoryId(int id);

    Collection<Item> findByUnitType(int unitType);

    Collection<Item> findByPrice(double price);

    Collection<Item> findAll();
}
