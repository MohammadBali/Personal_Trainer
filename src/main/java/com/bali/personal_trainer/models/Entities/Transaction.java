package com.bali.personal_trainer.models.Entities;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Table(name = "transaction")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @ManyToOne @JoinColumn(name = "userId") @JsonBackReference("user_transactions")
    private User userId;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "isRecurring")
    private boolean isRecurring;

    @Column(name = "recurrenceInterval")
    private String recurrenceInterval;

    @Column(name = "nextOccurrence")
    private Date nextOccurrence;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("transactions_items")
    private Collection<ItemTransaction> itemTransactions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public String getRecurrenceInterval() {
        return recurrenceInterval;
    }

    public void setRecurrenceInterval(String recurrenceInterval) {
        this.recurrenceInterval = recurrenceInterval;
    }

    public Date getNextOccurrence() {
        return nextOccurrence;
    }

    public void setNextOccurrence(Date nextOccurrence) {
        this.nextOccurrence = nextOccurrence;
    }

    public Collection<ItemTransaction> getItemTransactions() {
        return itemTransactions;
    }

    public void setItemTransactions(Collection<ItemTransaction> itemTransactions) {
        this.itemTransactions = itemTransactions;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", isRecurring=" + isRecurring +
                ", recurrenceInterval='" + recurrenceInterval + '\'' +
                ", nextOccurrence=" + nextOccurrence +
                ", itemTransactions=" + itemTransactions +
                '}';
    }
}
