package com.bali.personal_trainer.controllers;

import com.bali.personal_trainer.components.Components;
import com.bali.personal_trainer.models.Entities.User;
import com.bali.personal_trainer.services.UserService.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @Transactional(Transactional.TxType.REQUIRED)
    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody User user)
    {
        try
        {
            Object data = userService.registerUser(user);
            return ResponseEntity.ok(data);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error while signing up", "message", e.getMessage()));
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, Object> body)
    {
        try
        {
            String email = (String) body.get("email");
            String password= (String) body.get("password");

            Object data = userService.authenticateUser(email,password);
            if (data != null)
            {
                return ResponseEntity.ok(data);
            }
            else
            {
                return ResponseEntity.status(401).body(Map.of("error","Invalid credentials"));
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error while logging in", "message", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll()
    {
        try
        {
            Collection<User> users = userService.getAllUsers();

            ArrayList<Object> list = new ArrayList<>();

            for(User user : users)
            {
                list.add(Components.userToBeReturned(user,null, "password", "tokens"));
            }

            return ResponseEntity.ok(Map.of("users",list));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(Map.of("error","Error while getting all users","message",e.getMessage()));
        }
    }


    @GetMapping("/find/email")
    public ResponseEntity<?> getByEmail(@RequestBody Map<String, Object> body)
    {
        try
        {
            String email =(String) body.get("email");
            User u = userService.getUserByEmail(email);

            return ResponseEntity.ok(Components.userToBeReturned(u,null));
        }
        catch (Exception e)
        {
          return ResponseEntity.status(500).body(Map.of("error","Error while getting by email", "message",e.getMessage()));
        }
    }
}
