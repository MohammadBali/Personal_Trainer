package com.bali.personal_trainer.services.CategoryService;

import com.bali.personal_trainer.models.Entities.Category;
import com.bali.personal_trainer.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;

import javax.inject.Named;
import java.util.Collection;
import java.util.NoSuchElementException;

public interface CategoryService
{
    /**
     * Save a Category to Database
     * @param category Category Object
     * @throws IllegalArgumentException If transaction was null
     * @throws OptimisticLockingFailureException otherwise
     * @return Category Object - Inserted Data
     * **/
    Category save(Category category);

    /**
     * Find a Category by its ID
     * @param id Category ID
     * @throws NoSuchElementException if not found
     * @return Category Object
     * **/
    Category findById(int id);

    /**
     * Find a Category by its Name
     * @param name Category's Name
     * @throws NoSuchElementException if not found
     * @return Category Object
     * **/
    Category findByName(String name);

    /**
     * Get All Categories
     * @return Collection<Category>
     * **/
    Collection<Category> findAll();
}
