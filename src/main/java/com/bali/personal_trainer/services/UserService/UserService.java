package com.bali.personal_trainer.services.UserService;

import com.bali.personal_trainer.models.Entities.User;
import jakarta.transaction.Transactional;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Collection;
import java.util.NoSuchElementException;

public interface UserService {

    /**
     * Get User Data by ID
     * @param id User ID
     * @return User Object
     * @throws NoSuchElementException If user not found
     * **/
    User getUser(int id);

    /**
     * Get All Users
     * @return Collection of Users
     * **/
    Collection<User> getAllUsers();

    /**
     * Get a User by His Unique Email
     * @param email Email to be passed
     * @return User Object
     * @throws NoSuchElementException if not found
     * **/
    User getUserByEmail(String email);

    /**
     * Return user data by token
     * @param id The Extracted ID from Token
     * @return User Object
     * @throws NoSuchElementException if not found
     * **/
    User getUserByToken(int id);

    /**
     * Add a new User
     * @param user User Object Type
     * @return User Object - The Created one
     * @throws IllegalArgumentException, if user is null
     * @throws OptimisticLockingFailureException Otherwise
     * **/
    @Transactional(Transactional.TxType.REQUIRED)
    Object registerUser(User user);

    /**
     * Check login credentials, create token and return a Map of it
     * @param email User login email
     * @param password Password
     * @return Map of user data with this token
     * **/
    Object authenticateUser(String email, String password);

    /**
     * Patch a User
     * @param id User ID
     * @param user User Object to be updated
     * @return Updated User Object
     * @throws NoSuchElementException Wrong ID
     * @throws IllegalArgumentException, if user is null
     * @throws OptimisticLockingFailureException Otherwise
     * **/
    User updateUser(int id, User user);


}
