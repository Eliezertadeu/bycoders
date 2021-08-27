package com.bycorders.finance.factory;

import com.bycorders.finance.entity.Owner;
import com.bycorders.finance.entity.TransactionStore;
import com.bycorders.finance.factory.impl.TransactionFactoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TransactionFactoryTest {

    @Test
    void test__expectedSuccessWhenOwnerSizeIs4() throws IOException {
        TransactionFactoryImpl transactionFactory = new TransactionFactoryImpl();
        Assertions.assertEquals(4, transactionFactory.getOwners(multipartFile()).size());
    }

    @Test
    void test__expectedSuccessWhenStoreSizeIs5() throws IOException {
        TransactionFactoryImpl transactionFactory = new TransactionFactoryImpl();

        List<Owner> ownerList = new ArrayList<>(transactionFactory.getOwners(multipartFile()));

        Assertions.assertEquals(5, transactionFactory.getStoresWithOnwers(multipartFile(),ownerList).size());
    }

    @Test
    void test__expectedSuccessWhenTransactionsSizeIs21() throws Exception {
        TransactionFactoryImpl transactionFactory = new TransactionFactoryImpl();

        List<Owner> ownerList = new ArrayList<>(transactionFactory.getOwners(multipartFile()));
        List<TransactionStore> stores = new ArrayList<>(transactionFactory.getStoresWithOnwers(multipartFile(),ownerList));

        Assertions.assertEquals(21, transactionFactory.getTransactions(multipartFile(), stores, new ArrayList<>()).size());
    }

    private MultipartFile multipartFile() throws IOException {
        String pathVariable = "src/test/data/cnab.txt";

        File filePath = new File(pathVariable);
        FileInputStream fileInputStream = new FileInputStream(filePath);

        String name = "cnab.txt";
        String originalFileName = "cnab.txt";
        String contentType = "text/plain";
        byte[] content = fileInputStream.readAllBytes();

        return new MockMultipartFile(name, originalFileName, contentType, content);
    }
}
