package com.bycorders.finance.service.impl;

import com.bycorders.finance.entity.Owner;
import com.bycorders.finance.entity.Transaction;
import com.bycorders.finance.entity.TransactionStore;
import com.bycorders.finance.entity.TransactionType;
import com.bycorders.finance.factory.ITranslateStringToFile;
import com.bycorders.finance.repository.ITransactionRepository;
import com.bycorders.finance.service.IOwnerService;
import com.bycorders.finance.service.ITransactionService;
import com.bycorders.finance.service.ITransactionStoreService;
import com.bycorders.finance.service.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private ITransactionRepository transactionRepository;

    private ITransactionStoreService storeService;

    private ITransactionTypeService typeService;

    private IOwnerService ownerService;

    @Override
    public void saveTransactions(ITranslateStringToFile translateStringToFile, MultipartFile file) throws Exception {
        List<Owner> owners = saveAndGetAllOwners(translateStringToFile.getOwners(file));
        List<TransactionStore> stores = saveAndGetAllStore(translateStringToFile.getStoresWithOnwers(file, owners));
        transactionRepository.saveAll(translateStringToFile.getTransactions(file, stores, getAllTransactionsTypes()));
    }

    private List<Owner> saveAndGetAllOwners(Set<Owner> owners) {
        List<Owner> ownerSaved = ownerService.findAll();
        for(Owner owner : owners) {
            if(!ownerSaved.contains(owner)) {
                ownerService.save(owner);
            }
        }
        return ownerService.findAll();
    }

    private List<TransactionStore> saveAndGetAllStore(Set<TransactionStore> stores) {
        List<TransactionStore> storeSaved = storeService.findAll();

        for(TransactionStore store : stores) {
            if(!storeSaved.contains(store)) {
                storeService.save(store);
            }
        }
        return storeService.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    private List<TransactionType> getAllTransactionsTypes() {
        return typeService.getAllTypes();
    }

    @Autowired
    public void setTransactionRepository(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    public void setOwnerService(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Autowired
    public void setStoreService(ITransactionStoreService storeService) {
        this.storeService = storeService;
    }

    @Autowired
    public void setTypeService(ITransactionTypeService typeService) {
        this.typeService = typeService;
    }
}
