package com.bycorders.finance.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_TRANSACTION_STORE")
public class TransactionStore implements Serializable {

    public TransactionStore(){ }

    public TransactionStore(String storeName) {
        this.storeName = storeName;
    }

    private static final Long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TS_ID")
    private Long id;

    @Column(name = "TS_STORE_NAME")
    private String storeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_OW_ID", referencedColumnName = "OW_ID")
    private Owner owner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "transactionStore")
    private Set<Transaction> transactions = new HashSet<>();

    public BigDecimal getTotal() {
        return transactions.stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getInputs() {
        return transactions.stream()
                .map(Transaction::getType)
                .map(TransactionType::getSignal)
                .filter("+"::equals).count();
    }

    public Long getOutputs() {
        return transactions.stream()
                .map(Transaction::getType)
                .map(TransactionType::getSignal)
                .filter("-"::equals).count();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionStore that = (TransactionStore) o;
        return Objects.equals(storeName, that.storeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeName);
    }
}
