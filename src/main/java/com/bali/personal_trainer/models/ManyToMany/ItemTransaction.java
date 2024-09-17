package com.bali.personal_trainer.models.ManyToMany;

import com.bali.personal_trainer.models.Entities.Item;
import com.bali.personal_trainer.models.Entities.Transaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference("transactions_items")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "itemID", nullable = false)
    @JsonBackReference("items_transactions")
    private Item item;

    @Column(name = "totalUnitPrice")
    private double totalUnitPrice;

    // Additional attributes as needed, e.g., quantity
    @Column(name = "quantity")
    private double quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getTotalUnitPrice() {
        return totalUnitPrice;
    }

    public void setTotalUnitPrice(double totalUnitPrice) {
        this.totalUnitPrice = totalUnitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemTransaction{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", item=" + item +
                ", totalUnitPrice=" + totalUnitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
