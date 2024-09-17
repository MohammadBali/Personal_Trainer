package com.bali.personal_trainer.services.TypeService;
import com.bali.personal_trainer.models.Entities.Type;
import com.bali.personal_trainer.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Collection;
import java.util.NoSuchElementException;

@Named
public class TypeServiceImp implements TypeService
{
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type addType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type findTypeById(int id) {
        return typeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No Such type with Such Id:" + id));
    }

    @Override
    public Collection<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type updateById(Type type)
    {
        return typeRepository.save(type);
    }

    @Override
    public void deleteById(int id)
    {
        typeRepository.deleteById(id);
    }
}
