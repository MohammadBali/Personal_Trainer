package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    @Query("SELECT role FROM Role role WHERE role.name= :name")
    public Optional<Role> findByName(@Param("name") String name);
}
