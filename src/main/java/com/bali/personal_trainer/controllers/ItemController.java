package com.bali.personal_trainer.controllers;

import com.bali.personal_trainer.models.Entities.Item;
import com.bali.personal_trainer.repositories.ItemRepository;
import com.bali.personal_trainer.services.ItemService.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController
{
    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody Item item)
    {
        try
        {
            return ResponseEntity.ok().body(itemService.addItem(item));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllItems()
    {
        try
        {
            return ResponseEntity.ok(itemService.findAll());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","while getting all items", "message", e.getMessage()));
        }
    }

    @GetMapping("/find/name")
    public ResponseEntity<?> findItemByName(@RequestBody Map<String,Object> body)
    {
        try
        {
            return ResponseEntity.ok(itemService.findByName((String) body.get("name")));
        }

        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","while getting item by name", "message", e.getMessage()));
        }
    }

    @GetMapping("/find/id")
    public ResponseEntity<?> findItemById(@RequestBody Map<String,Object> body)
    {
        try
        {
            return ResponseEntity.ok(itemService.findById((int) body.get("id")));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","error while getting item by id", "message", e.getMessage()));
        }
    }

    @GetMapping("/find/type")
    public ResponseEntity<?> findItemByUnitType(@RequestBody Map<String,Object> body)
    {
        try
        {
            int unitType = ((Number) body.get("type")).intValue();
            return ResponseEntity.ok(itemService.findByUnitType(unitType));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","while getting item by type", "message", e.getMessage()));
        }
    }

    @GetMapping("/find/price")
    public ResponseEntity<?> findByPrice(@RequestBody Map<String,Object> body)
    {
        try
        {
            double price = ((Number) body.get("price")).doubleValue();
            return ResponseEntity.ok(itemService.findByPrice(price));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","error while getting item by price", "message", e.getMessage()));
        }
    }

    @GetMapping("/find/categoryId")
    public ResponseEntity<?> findByCategoryId(@RequestBody Map<String,Object> body)
    {
        try
        {
            return ResponseEntity.ok().body(itemService.findByCategoryId(Integer.parseInt((String) body.get("categoryId"))));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","error while getting item by categoryID", "message", e.getMessage()));
        }
    }
}
