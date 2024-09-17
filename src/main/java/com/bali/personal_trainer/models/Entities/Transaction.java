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


}
