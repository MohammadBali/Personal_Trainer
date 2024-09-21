package com.bali.personal_trainer.models.Entities;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Table(name = "transaction")
@Entity
public class Transaction
{

    public Transaction(){super();}

    public Transaction(User userId, double totalPrice, boolean isRecurring, String recurrenceInterval, Date nextOccurrence)
    {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.isRecurring = isRecurring;
        this.recurrenceInterval = recurrenceInterval;
        this.nextOccurrence = nextOccurrence;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @ManyToOne @JoinColumn(name = "userId") @JsonBackReference("user_transactions")
    @NotNull(message = "User ID cannot be null")
    private User userId;

    @Column(name = "totalPrice")
    @Min(0)
    private double totalPrice;

    @Column(name = "isRecurring")
    @ColumnDefault("FALSE")
    private boolean isRecurring;

    @Column(name = "recurrenceInterval")
    private String recurrenceInterval;

    @Column(name = "nextOccurrence")
    private Date nextOccurrence;

    @CreationTimestamp
    @Column(name = "createdAt")
    private Date createdAt;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
                ", createdAt=" + createdAt +
                ", itemTransactions=" + itemTransactions +
                '}';
    }
}
