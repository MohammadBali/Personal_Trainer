package com.bali.personal_trainer.services.RoleService;

import com.bali.personal_trainer.models.Entities.Role;

public interface RoleService
{
    Role findByName(String roleName);

    Role findById(int id);
}
