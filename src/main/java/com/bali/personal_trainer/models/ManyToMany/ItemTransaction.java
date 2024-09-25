package com.bali.personal_trainer.models.ManyToMany;

import com.bali.personal_trainer.models.Entities.Transaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "item_transaction")
public class ItemTransaction
{
    public ItemTransaction(){super();}

    public ItemTransaction(Transaction transaction, UserItem userItem,  double quantity, Date date) {
        this.transaction = transaction;
        this.userItem = userItem;
        this.totalUnitPrice = 0;
        this.quantity = quantity;
        this.date = date;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "transactionID", nullable = false)
    @JsonBackReference("transactions_items")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name="userItemID", nullable = false)
    @JsonBackReference("userItems_itemTransactions")
    private UserItem userItem;

    @Column(name = "totalUnitPrice")
    private double totalUnitPrice;

    @Column(name = "quantity")
    private double quantity;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Date date;

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

    public UserItem getUserItem() {
        return userItem;
    }

    public void setUserItem(UserItem userItem) {
        this.userItem = userItem;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @JsonProperty("itemName") // This makes sure 'itemName' appears in the JSON response
    public String getItemName() {
        return this.userItem.getItem().getName();
    }

    @Override
    public String toString() {
        return "ItemTransaction{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", userItem=" + userItem +
                ", totalUnitPrice=" + totalUnitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
