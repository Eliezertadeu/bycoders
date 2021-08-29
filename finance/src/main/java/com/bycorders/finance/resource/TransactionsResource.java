package com.bycorders.finance.resource;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import com.bycorders.finance.entity.Transaction;
import com.bycorders.finance.entity.TransactionStore;
import com.bycorders.finance.service.ITransactionService;
import org.springframework.web.bind.annotation.*;
import com.bycorders.finance.service.ITransactionStoreService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class TransactionsResource {

    private ITransactionService transactionService;

    private ITransactionStoreService storeService;

    @Value("${cors.origin-url}")
    private String origim;

    @GetMapping("/transactions")
    @ApiOperation(value = "Get all transactions saved")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        System.out.println(origim);
        return ResponseEntity.ok().body(transactionService.getTransactions());
    }

    @GetMapping("/transaction/{id}")
    @ApiParam(value = "Id of transaction")
    @ApiOperation(value = "Get one transaction")
    public ResponseEntity<Transaction> getOneTransaction(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/transactions-by-store")
    @ApiOperation(value = "Get all transactions grouped by store")
    public ResponseEntity<List<TransactionStore>> getTransactionsGroupedByStore() {
        return ResponseEntity.ok().body(storeService.findAll());
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
