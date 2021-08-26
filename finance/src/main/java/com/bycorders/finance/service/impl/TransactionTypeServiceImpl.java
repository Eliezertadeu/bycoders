package com.bycorders.finance.service.impl;

import com.bycorders.finance.entity.TransactionType;
import com.bycorders.finance.repository.ITransactionTypeRepository;
import com.bycorders.finance.service.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeServiceImpl implements ITransactionTypeService {

    private ITransactionTypeRepository typeRepository;

    public List<TransactionType> getAllTypes() {
        return typeRepository.findAll();
    }

    @Autowired
    public void setTypeRepository(ITransactionTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }
}
