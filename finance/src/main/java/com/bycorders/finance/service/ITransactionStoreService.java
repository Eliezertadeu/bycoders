package com.bycorders.finance.service;

import com.bycorders.finance.entity.TransactionStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface ITransactionStoreService {
    List<TransactionStore> saveAll(Set<TransactionStore> storesWithOnwers);

    List<TransactionStore> findAll();

    void save(TransactionStore store);
}
