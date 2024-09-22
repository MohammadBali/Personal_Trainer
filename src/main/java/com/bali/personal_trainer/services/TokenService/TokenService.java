package com.bali.personal_trainer.services.TokenService;
import com.bali.personal_trainer.models.Entities.Token;
import org.springframework.dao.OptimisticLockingFailureException;
import java.util.Collection;
import java.util.NoSuchElementException;

public interface TokenService
{
    /**
     * Return All Tokens of a User
     * @param userId User's ID
     * @throws NoSuchElementException If UserID is wrong
     * @return Collection of Tokens Object
     * **/
    Collection<Token> findTokenByUserId(int userId);

    /**
     * Add a new Token
     * @param token Token Object Type
     * @throws NoSuchElementException if not found
     * @throws OptimisticLockingFailureException If elements weren't accessible
     * @return Saved Token Type
     * **/
    Token save(Token token);

    /**
     * Removes a Token by Token Name ~ Not ID
     * @param token Token Value
     * @param userID UserID
     * @return String
     * @throws RuntimeException if some exception
     */
    String removeTokenByTokenAndUserId(String token, int userID);

}
