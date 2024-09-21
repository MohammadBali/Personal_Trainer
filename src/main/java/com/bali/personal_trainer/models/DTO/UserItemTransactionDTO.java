package com.bali.personal_trainer.models.DTO;

import jakarta.validation.constraints.Min;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;

/**
 * This Class if for items to be passed when creating a transaction, it has the itemID and it's quantity
 * **/
public class UserItemTransactionDTO
{
    @Min(value = 1, message = "itemID should be greater than 0")
    private int itemID;

    @Min(value = 1, message = "quantity should be greater than 0")
    private double quantity;

    @ColumnDefault(value = "-1")
    private int limit;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(@Min(value = 1, message = "itemID should be greater than 0") int itemID) {
        this.itemID = itemID;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 1, message = "quantity should be greater than 0") double quantity) {
        this.quantity = quantity;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    @Override
    public String toString() {
        return "ItemTransactionDTO{" +
                "itemID=" + itemID +
                ", quantity=" + quantity +
                '}';
    }
}
