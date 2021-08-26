package com.bycorders.finance.repository;

import com.bycorders.finance.entity.TransactionStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionStoreRepository extends JpaRepository<TransactionStore, Long> {
}
