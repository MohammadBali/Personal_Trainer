package com.bali.personal_trainer.models.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Table(name = "type")
@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "unitType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("type_items")
    private Collection<Item> items = new ArrayList<>();
}
