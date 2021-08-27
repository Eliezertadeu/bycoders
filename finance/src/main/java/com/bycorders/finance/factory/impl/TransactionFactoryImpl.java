package com.bycorders.finance.factory.impl;

import com.bycorders.finance.entity.Owner;
import com.bycorders.finance.entity.Transaction;
import com.bycorders.finance.entity.TransactionStore;
import com.bycorders.finance.entity.TransactionType;
import com.bycorders.finance.factory.ITranslateStringToFile;
import com.bycorders.finance.util.FileUtil;
import org.reflections.Store;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransactionFactoryImpl implements ITranslateStringToFile {

    @Override
    public Set<TransactionStore> getStoresWithOnwers(MultipartFile file, List<Owner> owners) throws IOException {

        Set<TransactionStore> stores = new HashSet<>();

        for(String register : toString(file)) {

            TransactionStore store = new TransactionStore(FileUtil.getTransactionStoreName(register));
            store.setOwner(owners.stream()
                    .filter(x -> x.getName().equals(FileUtil.getTransactionStoreOwner(register)))
                    .findFirst().orElse(null));

            stores.add(store);
        }
        return stores;
    }

    @Override
    public List<Transaction> getTransactions(MultipartFile file,
                                             List<TransactionStore> stores,
                                             List<TransactionType> transactionTypes) throws Exception {
        return extractTransactions(toString(file), stores, transactionTypes);
    }

    public Set<Owner> getOwners(MultipartFile file) throws IOException {
        return extractOwners(toString(file));
    }

    private List<Transaction> extractTransactions(String[] records,
                                                  List<TransactionStore> stores,
                                                  List<TransactionType> types) throws ParseException {

        List<Transaction> transactions = new ArrayList<>();

        for(String transact : records) {
            Transaction transaction = new Transaction();
            transaction.setCpf(FileUtil.getTransactionCpf(transact));
            transaction.setHour(FileUtil.getTransactionHour(transact));
            transaction.setDate(FileUtil.getTransactionDate(transact));
            transaction.setValue(FileUtil.getTransactionValue(transact).divide(BigDecimal.valueOf(100)));
            transaction.setCreditCard(FileUtil.getTransactionCreditCard(transact));

            transaction.setTransactionStore(stores.stream()
                    .filter(x -> x.getStoreName().equals(FileUtil.getTransactionStoreName(transact)))
                    .findFirst().orElse(null)
            );

            transaction.setType(types.stream()
                    .filter(x -> x.getId().equals(FileUtil.getTransactionType(transact)))
                    .findFirst().orElse(null)
            );

            transactions.add(transaction);
        }

        return transactions;
    }

    public Set<Owner> extractOwners(String[] records) {
        Set<Owner> owners = new HashSet<>();
        for(String transaction : records) {
            owners.add(new Owner(FileUtil.getTransactionStoreOwner(transaction)));
        }
        return owners;
    }

    public Set<TransactionStore> extractStores(String[] records) {
        Set<TransactionStore> transactions = new HashSet<>();
        for(String transaction : records) {
            transactions.add(new TransactionStore(FileUtil.getTransactionStoreName(transaction)));
        }
        return transactions;
    }
}
