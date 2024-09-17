package com.bali.personal_trainer.services.TypeService;

import com.bali.personal_trainer.models.Entities.Type;

import java.util.Collection;

public interface TypeService
{
    Type addType(Type type);

    Type findTypeById(int id);

    Collection<Type> findAll();

    Type updateById(Type type);

    void deleteById(int id);

}
