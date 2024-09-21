package com.bali.personal_trainer.models.DTO;

import com.bali.personal_trainer.models.Entities.Category;

public class ItemCategoryDTO
{
    private int id;
    private String name;

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


    public static ItemCategoryDTO convertToDTO(Category category) {
        if (category == null) {
            return null; // Handle null category case
        }

        ItemCategoryDTO categoryDTO = new ItemCategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }


    @Override
    public String toString() {
        return "ItemCategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
