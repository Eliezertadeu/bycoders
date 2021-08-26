package com.bycorders.finance.resource;

import com.bycorders.finance.entity.Owner;
import com.bycorders.finance.entity.Transaction;
import com.bycorders.finance.entity.TransactionStore;
import com.bycorders.finance.factory.impl.TransactionFactoryImpl;
import com.bycorders.finance.service.IOwnerService;
import com.bycorders.finance.service.ITransactionService;
import com.bycorders.finance.service.ITransactionStoreService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@Api("Resource to Upload Transactions of File")
public class ReadFileResource {

    private ITransactionService transactionService;

    private ITransactionStoreService storeService;

    @PostMapping("/transactions-file")
    @ApiOperation(value = "Save transactions of file")
    public ResponseEntity<Void> read(@RequestParam MultipartFile file) throws Exception {
        if (isMediaTypeSupported(file)) {
            throw new HttpMediaTypeNotSupportedException("Seu arquivo esta fora dos padrões da CNAB");
        }
        transactionService.saveTransactions(new TransactionFactoryImpl(), file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/transactions")
    @ApiOperation(value = "Get all transactions")
    public ResponseEntity<List<Transaction>> transactions() {
        return ResponseEntity.ok().body(transactionService.getTransactions());
    }

    @GetMapping("/transactions/{id}")
    @ApiParam(value = "Id of transaction")
    @ApiOperation(value = "Get one transaction")
    public ResponseEntity<Transaction> getOneTransaction(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/transactions-by-owner")
    @ApiOperation(value = "Get all transactions grouped by store")
    public ResponseEntity<List<TransactionStore>> getTransactionsGroupedByStore() {
        return ResponseEntity.ok().body(storeService.findAll());
    }

    private boolean isMediaTypeSupported(MultipartFile file) {
        if (file != null && file.getContentType() != null) {
            return file.getContentType().equals(MediaType.TEXT_PLAIN.getType());
        }
        return false;
    }

    @Autowired
    public void setTransactionService(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setStoreService(ITransactionStoreService storeService) {
        this.storeService = storeService;
    }
}
