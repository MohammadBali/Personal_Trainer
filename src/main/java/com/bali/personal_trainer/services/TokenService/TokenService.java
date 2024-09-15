package com.bali.personal_trainer.services.TokenService;

import com.bali.personal_trainer.models.Entities.Token;

import java.util.Collection;
import java.util.Optional;

public interface TokenService
{
    Optional<Collection<Token>> findTokenByUserId(int userId);

    Token save(Token token);
}
