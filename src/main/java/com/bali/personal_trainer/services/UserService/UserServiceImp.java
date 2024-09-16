package com.bali.personal_trainer.services.UserService;

import com.bali.personal_trainer.components.Components;
import com.bali.personal_trainer.components.Security.JwtUtility;
import com.bali.personal_trainer.models.Entities.Token;
import com.bali.personal_trainer.models.Entities.User;
import com.bali.personal_trainer.repositories.UserRepository;
import com.bali.personal_trainer.services.TokenService.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Named
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;


    /**Get a Single User by ID **/
    @Override
    public User get(int id)
    {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    /**Get All Users in Database **/
    @Override
    public Collection<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    /**Get user by his email **/
    @Override
    public User getUserByEmail(String email)
    {
        return userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }

    /**Register New Users **/
    public Object registerUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password

        String tokenValue = jwtUtility.generateToken(user);

        User u = userRepository.save(user);

        Token token = new Token();
        token.setUserId(user);
        token.setToken(tokenValue);

        return Components.userToBeReturned(u,tokenValue,"tokens","password");
    }

    /**Authenticate a User in Login via Password **/
    public Object authenticateUser(String email, String password)
    {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent())
        {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword()))
            {
                //Generate token
                String tokenValue =  jwtUtility.generateToken(user);

                Token t = new Token();
                t.setUserId(user);
                t.setToken(tokenValue);

                tokenService.save(t);

                return Components.userToBeReturned(user, tokenValue, "password", "tokens");
            }
        }
        return null; // Invalid credentials
    }

    /**Authenticate a user via token**/
    public void authenticateUser(String token)
    {
        //Todo
    }

    /**Generate a Token for User **/
    public String generateUserToken(User user)
    {
        return jwtUtility.generateToken(user);
    }

}