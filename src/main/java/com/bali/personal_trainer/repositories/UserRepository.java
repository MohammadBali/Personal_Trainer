package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("SELECT user FROM User user WHERE user.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT token.userId FROM Token token WHERE token.token =:token AND token.userId.id = :userId")
    Optional<User> findByTokenAndUserId(@Param("token") String token, @Param("userId") int userId);
}
