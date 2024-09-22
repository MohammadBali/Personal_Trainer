package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.Entities.Token;
import com.bali.personal_trainer.models.Entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer>
{
    @Query("SELECT token FROM Token token WHERE token.userId= :userId")
    Optional<Collection<Token>> findTokenByUserId(@Param("userId") int userId);

    @Modifying @Transactional
    @Query("DELETE FROM Token token WHERE token.token= :token AND token.userId.id= :userId")
    void deleteByTokenAndUserId(String token, int userId);

}
