package com.bycorders.finance.service;

import com.bycorders.finance.entity.TransactionType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITransactionTypeService {
    List<TransactionType> getAllTypes();
}
