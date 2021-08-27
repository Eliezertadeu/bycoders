package com.bycorders.finance.resource;

import com.bycorders.finance.factory.impl.TransactionFactoryImpl;
import com.bycorders.finance.service.ITransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
@Api("Resource to Upload Transactions of File")
public class TransactionFileUploadResource {

    private ITransactionService transactionService;

    @PostMapping("/transactions-file-upload")
    @ApiOperation(value = "Save transactions of file")
    public ResponseEntity<Void> fileUpload(@RequestParam MultipartFile file) throws Exception {
        if (isNotMediaTypeSupported(file)) {
            throw new HttpMediaTypeNotSupportedException("Seu arquivo está fora dos padrões da CNAB");
        }
        transactionService.saveTransactions(new TransactionFactoryImpl(), file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private boolean isNotMediaTypeSupported(MultipartFile file) {
        if (file != null && file.getContentType() != null) {
            return !file.getContentType().equals(MediaType.TEXT_PLAIN_VALUE);
        }
        return false;
    }

    @Autowired
    public void setTransactionService(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
