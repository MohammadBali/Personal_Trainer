package com.bali.personal_trainer.models.DTO;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.util.List;

public class TransactionDTO
{
    @Min(value = 1, message = "userId should be greater than 0")
    private int userId;
    @Min(value = 0, message = "totalPrice should be at least 0")
    private double totalPrice;
    @ColumnDefault("FALSE")
    private boolean isRecurring;
    private String recurrenceInterval;
    private Date nextOccurrence;
    private List<ItemTransaction> itemTransactions;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public List<ItemTransaction> getItemTransactions() {
        return itemTransactions;
    }

    public void setItemTransactions(List<ItemTransaction> itemTransactions) {
        this.itemTransactions = itemTransactions;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", isRecurring=" + isRecurring +
                ", recurrenceInterval='" + recurrenceInterval + '\'' +
                ", nextOccurrence=" + nextOccurrence +
                ", itemTransactions=" + itemTransactions +
                '}';
    }
}
