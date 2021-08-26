package com.bycorders.finance.factory;

import com.bycorders.finance.entity.Owner;
import com.bycorders.finance.entity.Transaction;
import com.bycorders.finance.entity.TransactionStore;
import com.bycorders.finance.entity.TransactionType;
import com.bycorders.finance.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ITranslateStringToFile {

    default String[] toString(MultipartFile file) throws IOException {
        return FileUtil.getBody(file);
    }

    Set<TransactionStore> getStoresWithOnwers(MultipartFile file, List<Owner> owners) throws IOException;

    List<Transaction> getTransactions(MultipartFile file, List<TransactionStore> stores,
                                      List<TransactionType> transactionTypes) throws Exception;

    Set<Owner> getOwners(MultipartFile file) throws IOException;
}
