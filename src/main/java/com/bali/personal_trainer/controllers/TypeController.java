package com.bali.personal_trainer.controllers;

import com.bali.personal_trainer.models.Entities.Type;
import com.bali.personal_trainer.services.TypeService.TypeService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/type")
public class TypeController
{
    @Autowired
    private TypeService typeService;

    @PostMapping("/add")
    public ResponseEntity<?> addType(@Valid @RequestBody Type type, BindingResult bindingResult)
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

            return ResponseEntity.ok().body(typeService.addType(type));
        }
        catch (ValidationException error)
        {
            return ResponseEntity.status(500).body(Map.of("error","Validation Error while adding type", "message", error.getMessage()));
        }

        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error", "Error while adding type", "message", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTypes()
    {
        try
        {
            Collection<Type> t= typeService.findAll();

            return ResponseEntity.ok(Map.of("Types",t));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error", "Error while getting all types", "message", e.getMessage()));
        }
    }

    @GetMapping("/find/id")
    public ResponseEntity<?> findById(@RequestBody Map<String, Object> body)
    {
        try
        {
            int id = (int) body.get("id");
            return ResponseEntity.ok(typeService.findTypeById(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error", "Error while getting type by id", "message", e.getMessage()));
        }
    }

    @PatchMapping("/patch/id")
    public ResponseEntity<?> patchById(@RequestBody Type type)
    {
        try
        {
            return ResponseEntity.ok(typeService.updateById(type));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error", "Error while patching type", "message", e.getMessage()));
        }
    }


    @DeleteMapping("/delete/id")
    public ResponseEntity<?> deleteById(@RequestBody Map<String, Object> body)
    {
        try
        {
            int id = (int) body.get("id");
            typeService.deleteById(id);
            return ResponseEntity.ok().body(Map.of("message","Type deleted successfully", "status", 1));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error", "Error while deleting type", "message", e.getMessage()));
        }
    }
}
