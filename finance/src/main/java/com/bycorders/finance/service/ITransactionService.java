package com.bycorders.finance.service;

import com.bycorders.finance.entity.Transaction;
import com.bycorders.finance.factory.ITranslateStringToFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    void saveTransactions(ITranslateStringToFile translateStringToFile, MultipartFile file) throws Exception;

    List<Transaction> getTransactions();

    Optional<Transaction> getTransactionById(Long id);
}
