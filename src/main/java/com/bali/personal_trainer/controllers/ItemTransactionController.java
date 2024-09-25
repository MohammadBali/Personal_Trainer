package com.bali.personal_trainer.controllers;

import com.bali.personal_trainer.components.Components;
import com.bali.personal_trainer.services.ItemTransactionService.ItemTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/itemTransaction")
public class ItemTransactionController
{
    @Autowired
    ItemTransactionService itemTransactionService;

    @GetMapping("/mostBoughtItem/ThisMonth")
    public ResponseEntity<?> findMostBoughtTransactionThisMonth()
    {
        int userId = Components.getUserIdFromToken();
        try
        {
            return ResponseEntity.ok(Map.of("Item",itemTransactionService.findMostBoughtItemThisMonth(userId)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While getting transaction","message",e.getMessage()));
        }
    }

    @GetMapping("/itemsOfDate")
    public ResponseEntity<?> itemsOfToday(@RequestBody Map<String,Object> body)
    {
        try
        {
            int userId = Components.getUserIdFromToken();
            LocalDate date = body.get("date")!=null? LocalDate.parse(body.get("date").toString()) : null;

            return ResponseEntity.ok(Map.of("items",itemTransactionService.findItemsOfDate(userId,date)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While getting items of today","message",e.getMessage()));
        }
    }

    @GetMapping("/search/category")
    public ResponseEntity<?> searchByCategory(@RequestBody Map<String,Object> body)
    {
        try
        {
            int userId = Components.getUserIdFromToken();
            int category = ((Number) body.get("category")).intValue();
            return ResponseEntity.ok(itemTransactionService.searchByCategory(userId, category));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error while searching by category", "message", e.getMessage()));
        }
    }
}
