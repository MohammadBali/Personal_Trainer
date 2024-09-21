package com.bali.personal_trainer.services.RoleService;

import com.bali.personal_trainer.models.Entities.Role;

import java.util.NoSuchElementException;

public interface RoleService
{
    /**
     * Find Role By Name
     * @param roleName Role's Name
     * @throws NoSuchElementException not found
     * @return Role Object
     * **/
    Role findByName(String roleName);

    /**
     * Find Role by ID
     * @param id Role's ID
     * @throws NoSuchElementException not found
     * @return Role Object
     * **/
    Role findById(int id);
}
