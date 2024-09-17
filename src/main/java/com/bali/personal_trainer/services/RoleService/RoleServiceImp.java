package com.bali.personal_trainer.services.RoleService;

import com.bali.personal_trainer.models.Entities.Role;
import com.bali.personal_trainer.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.NoSuchElementException;

@Named
public class RoleServiceImp implements RoleService
{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(()-> new NoSuchElementException("No Such Role with such name" + name));
    }

    @Override
    public Role findById(int id) {
        return roleRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No Such Role with such id" + id));
    }
}
