package com.bali.personal_trainer.models.Entities;

import com.bali.personal_trainer.models.ItemTransaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Table(name = "item")
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @ManyToOne @JoinColumn(name="categoryId") @JsonBackReference
    private Category categoryId;

    @ManyToOne @JoinColumn(name = "unitType") @JsonBackReference
    private Type unitType;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ItemTransaction> itemTransactions = new ArrayList<>();
}
