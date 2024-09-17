package com.bali.personal_trainer.services.UserService;

import com.bali.personal_trainer.models.Entities.User;

import java.util.Collection;

public interface UserService {

    User get(int id);

    Collection<User> getAllUsers();

    User getUserByEmail(String email);

    Object registerUser(User user);

    Object authenticateUser(String email, String password);

    User updateUser(int id, User user);


}
