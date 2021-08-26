package com.bycorders.finance.repository;

import com.bycorders.finance.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
