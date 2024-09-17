package com.bali.personal_trainer.controllers;

import com.bali.personal_trainer.models.Entities.Category;
import com.bali.personal_trainer.services.CategoryService.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/category")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category category, BindingResult bindingResult)
    {
        try
        {
            if(bindingResult.hasErrors())
            {
                // Collect all validation errors into a list
                List<String> errors = bindingResult.getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();

                throw new ValidationException(errors.toString());
            }

            Category c = categoryService.save(category);

            return ResponseEntity.ok().body(Map.of("Category", c));
        }

        catch (ValidationException e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Validation Error while creating a category", "message", e.getMessage()));
        }

        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error while creating a category", "message", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories()
    {
        try
        {
            Collection<Category> c= categoryService.findAll();

            ArrayList<Object> list = new ArrayList<>();

            for(Category cat : c)
            {
                list.add(cat);
            }

            return ResponseEntity.ok(Map.of("categories",list));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error while getting all categories", "message",e.getMessage()));
        }
    }

    @GetMapping("/find/id")
    public ResponseEntity<?> getCategoryById(@RequestBody Map<String,Object> body)
    {
        try
        {
            int id = (int) body.get("id");
            return ResponseEntity.ok().body(categoryService.findById(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error while getting category by id", "message",e.getMessage()));
        }
    }
}
