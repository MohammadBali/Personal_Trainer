package com.bali.personal_trainer.services.ItemService;

import com.bali.personal_trainer.models.DTO.ItemDTO;
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
    public ItemDTO findById(int id) throws IllegalAccessException {
        Item item = itemRepository.findById(id).orElseThrow(()->new NoSuchElementException("Item not found with id: " + id));
        return ItemDTO.convertToDTO(item);
    }

    @Override
    public ItemDTO findByName(String name) throws IllegalAccessException{

        Item item = itemRepository.findByName(name).orElseThrow(()->new NoSuchElementException("No Such Item with name" + name));
        return ItemDTO.convertToDTO(item);
    }

    @Override
    public Collection<ItemDTO> findByCategoryId(int id) throws IllegalAccessException{
        Collection<Item> items= itemRepository.findByCategoryId(id).orElseThrow(()->new NoSuchElementException("No Such Item with categoryId: " + id));

        return ItemDTO.convertMultipleToDTO(items);
    }

    @Override
    public Collection<ItemDTO> findByUnitType(int unitType) throws IllegalAccessException{
        Collection<Item> items= itemRepository.findByUnitType(unitType).orElseThrow(()->new NoSuchElementException("No Such Item with unitType: " + unitType));

        return ItemDTO.convertMultipleToDTO(items);
    }

    @Override
    public Collection<ItemDTO> findByPrice(double price) throws IllegalAccessException
    {
        Collection<Item> items= itemRepository.findByPrice(price).orElseThrow(()->new NoSuchElementException("No Such Item with price: " + price));
        return ItemDTO.convertMultipleToDTO(items);
    }

    @Override
    public Collection<ItemDTO> findAll() throws IllegalAccessException
    {
        Collection<Item> items= itemRepository.findAll();
        return ItemDTO.convertMultipleToDTO(items);
    }

    //Return Type was Object not Item
    @Override
    public Item addItem(Item item)
    {
        Category category = categoryService.findById(item.getCategoryId().getId());

        item.setCategoryId(category);

        return itemRepository.save(item);
    }
}
