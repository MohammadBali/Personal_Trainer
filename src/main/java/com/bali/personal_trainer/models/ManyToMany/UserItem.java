package com.bali.personal_trainer.models.ManyToMany;

import com.bali.personal_trainer.models.Entities.Item;
import com.bali.personal_trainer.models.Entities.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_item")
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "itemID")
    private Item item;

    @Column(name = "limit")
    private int limit;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private Date date;


}
