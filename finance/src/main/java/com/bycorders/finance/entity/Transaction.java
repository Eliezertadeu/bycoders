package com.bycorders.finance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TB_TRANSACTION")
public class Transaction implements Serializable {

    public Transaction () { }

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TR_ID")
    private Long id;

    @Column(name = "TR_DATE")
    private Date date;

    @Column(name = "TR_VALUE")
    private BigDecimal value;

    @Column(name = "TR_CPF")
    private String cpf;

    @Column(name = "TR_CREDIT_CARD")
    private String creditCard;

    @Column(name = "TR_HOUR")
    private String hour;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_TS_ID", referencedColumnName = "TS_ID")
    private TransactionStore transactionStore;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_TP_ID", referencedColumnName = "TP_ID")
    private TransactionType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        if(type != null && type.getSignal().equals("-")) {
            value = value.negate();
        }
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionStore getTransactionStore() {
        return transactionStore;
    }

    public void setTransactionStore(TransactionStore transactionStore) {
        this.transactionStore = transactionStore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(date, that.date) && Objects.equals(cpf, that.cpf) && Objects.equals(hour, that.hour) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, cpf, hour, type);
    }
}
