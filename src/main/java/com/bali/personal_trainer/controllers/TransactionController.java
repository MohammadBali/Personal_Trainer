package com.bali.personal_trainer.controllers;

import com.bali.personal_trainer.components.Components;
import com.bali.personal_trainer.components.Enums.PriceType;
import com.bali.personal_trainer.models.DTO.TransactionDTO;
import com.bali.personal_trainer.models.Entities.Transaction;
import com.bali.personal_trainer.services.TransactionService.TransactionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transaction")
public class TransactionController
{
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDTO transaction, BindingResult bindingResult)
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

            transaction.setUserId(Components.getUserIdFromToken());
            return ResponseEntity.ok(transactionService.createTransactionFromDTO(transaction));
        }

        catch (ValidationException error)
        {
            return ResponseEntity.status(500).body(Map.of("error","Validation Error while adding transaction", "message", error.getMessage()));
        }

        catch (Throwable e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error while adding new transaction","message",e.getMessage()!=null? e.getMessage() : ""));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllTransactions()
    {
        try
        {
            return ResponseEntity.ok(Map.of("transactions",transactionService.findAll()));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While getting all transactions","message",e.getMessage()));
        }
    }

    @GetMapping("/find/id")
    public ResponseEntity<?> findTransactionById(@RequestBody Map<String,Object> body)
    {
        try
        {
            int id = (int) body.get("id");
            return ResponseEntity.ok(transactionService.findById(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While getting transaction","message",e.getMessage()));
        }
    }

    @GetMapping("/find/totalPrice")
    public ResponseEntity<?> findTransactionsByTotalPrice(@RequestBody Map<String,Object> body)
    {
        try
        {
            double totalPrice = ((Number) body.get("totalPrice")).doubleValue();
            PriceType type= PriceType.valueOf((String) body.get("type"));

            return ResponseEntity.ok(Map.of("Transactions",transactionService.findByTotalPrice(totalPrice, type)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While getting transaction","message",e.getMessage()));
        }
    }

    @GetMapping("/find/totalPriceAndUserId")
    public ResponseEntity<?> findTransactionsByTotalPriceAndUserId(@RequestBody Map<String,Object> body)
    {
        try
        {
            double totalPrice = ((Number) body.get("totalPrice")).doubleValue();
            int userId = Components.getUserIdFromToken();

            PriceType type= PriceType.valueOf((String) body.get("type"));

            return ResponseEntity.ok(Map.of("Transactions",transactionService.findByTotalPriceAndUserId(totalPrice,userId, type)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While getting transaction","message",e.getMessage()));
        }
    }

    @GetMapping("/mostBoughtItem/ThisMonth")
    public ResponseEntity<?> findMostBoughtTransactionThisMonth()
    {
        int userId = Components.getUserIdFromToken();
        try
        {
            return ResponseEntity.ok(Map.of("Item",transactionService.findTopTransactionByHighestPriceThisMonth(userId)));
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

            return ResponseEntity.ok(Map.of("items",transactionService.findItemsOfToday(userId,date)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While getting items of today","message",e.getMessage()));
        }
    }

    @GetMapping("/find/userId")
    public ResponseEntity<?> findTransactionsByUserId()
    {
        try
        {
            int userId = Components.getUserIdFromToken();
            return ResponseEntity.ok(Map.of("Transactions",transactionService.findByUserId(userId)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While getting transactions","message",e.getMessage()));
        }
    }

    @PatchMapping("/patch")
    public ResponseEntity<?> patchTransaction(@RequestBody Transaction transaction)
    {
        try
        {
            // Extract the authenticated user's ID from the token
            int userId = Components.getUserIdFromToken();
            return ResponseEntity.ok(Map.of("Message",transactionService.updateTransaction(userId, transaction)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While updating transaction","message",e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTransactionById(@RequestBody Map<String,Object> body)
    {
        try
        {
            int id = (int) body.get("id");
            return ResponseEntity.ok(Map.of("message",transactionService.deleteTransaction(id), "status", 1));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error While deleting transaction","message",e.getMessage()));
        }
    }


}
