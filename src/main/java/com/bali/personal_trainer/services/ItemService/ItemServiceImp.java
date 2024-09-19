package com.bali.personal_trainer.services.ItemService;

import com.bali.personal_trainer.models.Entities.Category;
import com.bali.personal_trainer.models.Entities.Item;
import com.bali.personal_trainer.repositories.ItemRepository;
import com.bali.personal_trainer.services.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Collection;
import java.util.NoSuchElementException;

@Named
public class ItemServiceImp implements ItemService
{
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Item findById(int id)
    {
        Item item = itemRepository.findById(id).orElseThrow(()->new NoSuchElementException("Item not found with id: " + id));

        //Todo: cannot return the unitType or category?
        //System.out.println(item.toString());
        return item;
    }

    @Override
    public Item findByName(String name) {

        return itemRepository.findByName(name).orElseThrow(()->new NoSuchElementException("No Such Item with name" + name));
    }

    @Override
    public Collection<Item> findByCategoryId(int id) {
        return itemRepository.findByCategoryId(id).orElseThrow(()->new NoSuchElementException("No Such Item with categoryId: " + id));
    }

    @Override
    public Collection<Item> findByUnitType(int unitType) {
        return itemRepository.findByUnitType(unitType).orElseThrow(()->new NoSuchElementException("No Such Item with unitType: " + unitType));
    }

    @Override
    public Collection<Item> findByPrice(double price) {
        return itemRepository.findByPrice(price).orElseThrow(()->new NoSuchElementException("No Such Item with price: " + price));
    }

    @Override
    public Collection<Item> findAll()
    {
        return itemRepository.findAll();
    }

    @Override
    public Object addItem(Item item)
    {
        Category category = categoryService.findById(item.getCategoryId().getId());

        item.setCategoryId(category);

        return itemRepository.save(item);
    }
}
