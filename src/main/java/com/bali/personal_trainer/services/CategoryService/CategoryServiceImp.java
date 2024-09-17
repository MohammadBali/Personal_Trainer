package com.bali.personal_trainer.services.CategoryService;

import com.bali.personal_trainer.models.Entities.Category;
import com.bali.personal_trainer.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Named
public class CategoryServiceImp implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        System.out.println(category.toString());
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Category not found with id: " + id));
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name).orElseThrow(()-> new NoSuchElementException("Category not found with name: " + name));
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }
}
