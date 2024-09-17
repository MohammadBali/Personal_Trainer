package com.bali.personal_trainer.services.CategoryService;

import com.bali.personal_trainer.models.Entities.Category;
import com.bali.personal_trainer.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Collection;

public interface CategoryService
{
    Category save(Category category);

    Category findById(int id);

    Category findByName(String name);

    Collection<Category> findAll();
}
