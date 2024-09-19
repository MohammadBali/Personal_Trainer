package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer>
{
    Optional<Category> findById(int id);

    @Query("SELECT category FROM Category category WHERE category.name=:name")
    Optional<Category> findByNameIgnoreCase(@Param(value = "name") String name);


}
