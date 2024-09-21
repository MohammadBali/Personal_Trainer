package com.bali.personal_trainer.models.DTO;

import com.bali.personal_trainer.models.Entities.Item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemDTO
{
    private int id;

    private String name;

    private double price;

    private ItemCategoryDTO category;

    private ItemTypeDTO unitType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ItemCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(ItemCategoryDTO category) {
        this.category = category;
    }

    public ItemTypeDTO getUnitType() {
        return unitType;
    }

    public void setUnitType(ItemTypeDTO unitType) {
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", unitType=" + unitType +
                '}';
    }


    /**
     * Map Item to ItemDTO
     * @param item Item to be converted
     * @return ItemDTO The ItemDTO Object
     * @throws IllegalAccessException if error in setAccessible
     * **/
    public static ItemDTO convertToDTO(Item item) throws IllegalAccessException {
        ItemDTO itemDTO = new ItemDTO();

        // Get all fields of the Item class
        Field[] itemFields = Item.class.getDeclaredFields();

        // Get all fields of the ItemDTO class
        Field[] itemDTOFields = ItemDTO.class.getDeclaredFields();

        // Create a map to quickly find and set corresponding fields in the DTO
        Map<String, Field> itemDTOFieldMap = new HashMap<>();
        for (Field field : itemDTOFields) {
            itemDTOFieldMap.put(field.getName(), field);
        }

        // Set each field dynamically from Item to ItemDTO
        for (Field itemField : itemFields) {
            String fieldName = itemField.getName();

            // Skip fields that need manual conversion
            if (fieldName.equals("categoryId") || fieldName.equals("unitType")) {
                continue;
            }

            if (itemDTOFieldMap.containsKey(fieldName))
            {
                Field dtoField = itemDTOFieldMap.get(fieldName);
                itemField.setAccessible(true);
                dtoField.setAccessible(true);

                // Set the value in the DTO
                dtoField.set(itemDTO, itemField.get(item));
            }
        }

        // Set category and unitType using their DTOs
        itemDTO.setCategory(ItemCategoryDTO.convertToDTO(item.getCategoryId()));
        itemDTO.setUnitType(ItemTypeDTO.convertToDTO(item.getUnitType()));


        return itemDTO;
    }

    public static Collection<ItemDTO> convertMultipleToDTO(Collection<Item> items) throws IllegalAccessException {
        ArrayList<ItemDTO> itemsToReturn= new ArrayList<>();
        for(Item item : items)
        {
            itemsToReturn.add(convertToDTO(item));
        }

        return itemsToReturn;
    }
}
