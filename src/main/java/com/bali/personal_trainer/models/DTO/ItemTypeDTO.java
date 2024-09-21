package com.bali.personal_trainer.models.DTO;

import com.bali.personal_trainer.models.Entities.Type;

public class ItemTypeDTO
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

    public static ItemTypeDTO convertToDTO(Type unitType) {
        if (unitType == null) {
            return null; // Handle null unit type case
        }

        ItemTypeDTO unitTypeDTO = new ItemTypeDTO();
        unitTypeDTO.setId(unitType.getId());
        unitTypeDTO.setName(unitType.getName());

        return unitTypeDTO;
    }

    @Override
    public String toString() {
        return "ItemTypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
