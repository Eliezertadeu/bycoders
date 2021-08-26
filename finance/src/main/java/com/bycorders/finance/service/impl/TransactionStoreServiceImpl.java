package com.bycorders.finance.service.impl;

import com.bycorders.finance.entity.TransactionStore;
import com.bycorders.finance.repository.ITransactionStoreRepository;
import com.bycorders.finance.service.ITransactionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TransactionStoreServiceImpl implements ITransactionStoreService {

    private ITransactionStoreRepository storeRepository;

    @Override
    public List<TransactionStore> saveAll(Set<TransactionStore> stores) {
        return storeRepository.saveAll(stores);
    }

    @Override
    public List<TransactionStore> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public void save(TransactionStore store) {
        storeRepository.save(store);
    }

    @Autowired
    public void setStoreRepository(ITransactionStoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
}
