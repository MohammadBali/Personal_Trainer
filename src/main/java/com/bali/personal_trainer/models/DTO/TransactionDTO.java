package com.bali.personal_trainer.models.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import java.sql.Date;
import java.util.List;

public class TransactionDTO
{
    @Min(value = 1, message = "userId should be greater than 0")
    private int userId;

    @ColumnDefault(value = "0")
    private double totalPrice;

    @ColumnDefault("FALSE")
    private boolean isRecurring;

    private String recurrenceInterval;

    private Date nextOccurrence;

    @Valid @NotNull(message = "itemTransactions field shouldn't be null")
    private List<UserItemTransactionDTO> itemTransactions;

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

    public List<UserItemTransactionDTO> getItemTransactions() {
        return itemTransactions;
    }

    public void setItemTransactions(List<UserItemTransactionDTO> itemTransactions) {
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
