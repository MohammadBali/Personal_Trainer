package com.bali.personal_trainer.models.Entities;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
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

    @ManyToOne @JoinColumn(name="categoryId") @JsonBackReference("category_items")
    private Category categoryId;

    @ManyToOne @JoinColumn(name = "unitType") @JsonBackReference("type_items")
    private Type unitType;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("items_transactions")
    private Collection<ItemTransaction> itemTransactions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Type getUnitType() {
        return unitType;
    }

    public void setUnitType(Type unitType) {
        this.unitType = unitType;
    }

    public Collection<ItemTransaction> getItemTransactions() {
        return itemTransactions;
    }

    public void setItemTransactions(Collection<ItemTransaction> itemTransactions) {
        this.itemTransactions = itemTransactions;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", unitType=" + unitType +
                ", itemTransactions=" + itemTransactions +
                '}';
    }
}
