package com.bali.personal_trainer.services.UserService;

import com.bali.personal_trainer.components.Components;
import com.bali.personal_trainer.components.Security.JwtHandlers.JwtUtility;
import com.bali.personal_trainer.models.Entities.Role;
import com.bali.personal_trainer.models.Entities.Token;
import com.bali.personal_trainer.models.Entities.User;
import com.bali.personal_trainer.repositories.UserRepository;
import com.bali.personal_trainer.services.RoleService.RoleService;
import com.bali.personal_trainer.services.TokenService.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Named
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;


    /**Get a Single User by ID **/
    @Override
    public User getUser(int id)
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

    @Override
    public User getUserByToken(int id)
    {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    /**Register New Users **/
    public Object registerUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password

        if (user.getRole() == null) {
            Role defaultRole = roleService.findById(2);
            user.setRole(defaultRole);
        }

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

    @Override
    public User updateUser(int id, User user)
    {

        User u = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id to be patched: " + id));
        User updatedUser = Components.copyNonNullElements(user,u);

        if(updatedUser ==null)
        {
            throw new RuntimeException("Couldn't patch user with id: " + id);
        }

        return userRepository.save(updatedUser);
    }


//    /**Generate a Token for User **/
//    public String generateUserToken(User user)
//    {
//        return jwtUtility.generateToken(user);
//    }

}
