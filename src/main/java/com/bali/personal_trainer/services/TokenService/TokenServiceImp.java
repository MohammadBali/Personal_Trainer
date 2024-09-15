package com.bali.personal_trainer.services.TokenService;

import com.bali.personal_trainer.models.Entities.Token;
import com.bali.personal_trainer.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Collection;
import java.util.Optional;

@Named
public class TokenServiceImp implements TokenService
{
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public Optional<Collection<Token>> findTokenByUserId(int userId) {
        return tokenRepository.findTokenByUserId(userId);
    }

    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }
}
