package com.bycorders.finance.service;

import com.bycorders.finance.entity.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface IOwnerService {
    List<Owner> saveAll(Set<Owner> owners);

    List<Owner> findAll();

    void save(Owner owner);
}
