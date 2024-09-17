package com.bali.personal_trainer.models.Entities;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collection;

@Table(name = "item")
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="name", unique = true)
    @NotBlank(message = "name cannot be nullable")
    private String name;

    @Column(name="price")
    @NotNull(message = "price cannot be nullable")
    @Min(value = 0, message = "price value cannot be lower than 0")
    private double price;

    @ManyToOne @JoinColumn(name="categoryId") @JsonBackReference("category_items")
    @NotNull(message = "categoryId cannot be nullable")
    private Category categoryId;

    @ManyToOne @JoinColumn(name = "unitType") @JsonBackReference("type_items")
    @NotNull(message = "unitType cannot be nullable")
    private Type unitType;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("items_transactions")
    @JsonIgnore
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
