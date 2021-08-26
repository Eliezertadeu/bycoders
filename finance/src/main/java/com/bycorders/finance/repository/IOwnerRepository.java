package com.bycorders.finance.repository;

import com.bycorders.finance.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOwnerRepository extends JpaRepository<Owner, Long> {
}
