package com.bali.personal_trainer.models;

import com.bali.personal_trainer.models.Entities.Item;
import com.bali.personal_trainer.models.Entities.Transaction;
import jakarta.persistence.*;

@Entity
@Table(name = "item_transaction")
public class ItemTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "transactionID", nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "itemID", nullable = false)
    private Item item;

    @Column(name = "totalUnitPrice")
    private double totalUnitPrice;

    // Additional attributes as needed, e.g., quantity
    @Column(name = "quantity")
    private double quantity;

    // Todo Getters and setters...
}
