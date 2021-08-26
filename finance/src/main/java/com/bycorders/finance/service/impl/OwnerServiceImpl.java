package com.bycorders.finance.service.impl;

import com.bycorders.finance.entity.Owner;
import com.bycorders.finance.repository.IOwnerRepository;
import com.bycorders.finance.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OwnerServiceImpl implements IOwnerService {

    private IOwnerRepository ownerRepository;

    public List<Owner> saveAll (Set<Owner> owners){
        return ownerRepository.saveAll(owners);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public void save(Owner owner) {
        ownerRepository.save(owner);
    }

    public List<Owner> getAllOwners (Set<Owner> owners){
        return ownerRepository.findAll();
    }


    @Autowired
    public void setOwnerRepository(IOwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
}
