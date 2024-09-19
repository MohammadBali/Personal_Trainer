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

    @ManyToOne
    @JoinColumn(name="userItemID", nullable = false)
    @JsonBackReference("userItems_itemTransactions")
    private UserItem userItem;

    @Column(name = "totalUnitPrice")
    private double totalUnitPrice;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public UserItem getUserItem() {
        return userItem;
    }

    public void setUserItem(UserItem userItem) {
        this.userItem = userItem;
    }

    @Override
    public String toString() {
        return "ItemTransaction{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", item=" + item +
                ", userItem=" + userItem +
                ", totalUnitPrice=" + totalUnitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
