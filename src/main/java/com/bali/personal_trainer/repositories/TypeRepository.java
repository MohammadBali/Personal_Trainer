package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.Entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Integer>
{
    @Query("SELECT type FROM Type type WHERE type.name= :name")
    Optional<Type> findByName(@Param("name") String name);

//    @Modifying
//    @Query("UPDATE Type type SET type.name=:name WHERE type.id=:id ")
//    int updateTypeByName(@Param("id") int id, @Param("name") String name);
}
