package com.bali.personal_trainer.repositories;

import com.bali.personal_trainer.models.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer>
{
    Optional<Item> findByName(String name);

    @Query("SELECT item FROM Item item WHERE item.categoryId.id = :id")
    Optional<Collection<Item>> findByCategoryId(@Param(value = "id") int id);

    @Query("SELECT item FROM Item item WHERE item.unitType.id= :unitType")
    Optional<Collection<Item>> findByUnitType(@Param(value = "unitType") int unitType);

    Optional<Collection<Item>> findByPrice(@Param(value = "price") double price);

}
