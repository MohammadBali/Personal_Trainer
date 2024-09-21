package com.bali.personal_trainer.models.ManyToMany;

import com.bali.personal_trainer.models.Entities.Item;
import com.bali.personal_trainer.models.Entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user_item")
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @ManyToOne @JoinColumn(name = "userID") @JsonBackReference("user_items")
    private User user;

    @ManyToOne @JoinColumn(name = "itemID") @JsonBackReference
    private Item item;

    @Column(name = "limit")
    @ColumnDefault(value = "-1")
    private int limit;

    @Column(name = "updatedAt")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(mappedBy = "userItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("userItems_itemTransactions")
    private Collection<ItemTransaction> itemTransactions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date date) {
        this.updatedAt = date;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "id=" + id +
                ", user=" + user +
                ", item=" + item +
                ", limit=" + limit +
                ", date=" + updatedAt +
                '}';
    }
}
