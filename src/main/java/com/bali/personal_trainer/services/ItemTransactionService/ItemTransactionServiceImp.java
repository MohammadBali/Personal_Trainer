package com.bali.personal_trainer.services.ItemTransactionService;

import com.bali.personal_trainer.models.ManyToMany.ItemTransaction;
import com.bali.personal_trainer.repositories.ItemTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Named
public class ItemTransactionServiceImp implements ItemTransactionService{

    @Autowired
    ItemTransactionRepository itemTransactionRepository;

    @Override
    public ItemTransaction add(ItemTransaction itemTransaction) {
        return itemTransactionRepository.save(itemTransaction);
    }

    @Override
    public ItemTransaction findById(int id) {
        return itemTransactionRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Couldn't find itemTransaction with such ID" + id));
    }

    @Override
    public Collection<ItemTransaction> findByTransactionId(int transactionId) {
        return itemTransactionRepository.findByTransactionId(transactionId).orElseThrow(()->  new NoSuchElementException("Couldn't find itemTransactions with such transactionID" + transactionId));
    }

    @Override
    public Collection<ItemTransaction> findByUserId(int userId) {
        return itemTransactionRepository.findByUserId(userId).orElseThrow(()-> new NoSuchElementException("Couldn't find itemTransactions with such userID" + userId));
    }

    @Override
    public Collection<ItemTransaction> findByUserItemId(int userItemId) {
        return itemTransactionRepository.findByUserItemId(userItemId).orElseThrow(()-> new NoSuchElementException("Couldn't find itemTransactions with such userItemID" + userItemId));
    }

    @Override
    public Collection<ItemTransaction> findAll() {
        return itemTransactionRepository.findAll();
    }

    @Override
    public Collection<ItemTransaction> findMostBoughtItemInHighestTransactionThisMonth(int userId, LocalDate startOfMonth, LocalDate endOfMonth)
    {

        return itemTransactionRepository.findMostBoughtItemInHighestTransactionThisMonth(userId, startOfMonth, endOfMonth).orElseThrow(
                ()-> new NoSuchElementException("No Such Transaction was found")
        );
    }

    @Override
    public Collection<ItemTransaction> findItemsOfDate(int userId, LocalDate date) {
        return itemTransactionRepository.findItemsOfDate(userId,date).orElseThrow(
                ()-> new NoSuchElementException("No Such ItemTransactions were found for this date" + date.toString()));
    }
}
