package com.bycorders.finance.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_TRANSACTION_TYPE")
public class TransactionType implements Serializable {

    public TransactionType () {}

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TP_ID")
    private Long id;

    @Column(name = "TP_DESCRIPTION")
    private String description;

    @Column(name = "TP_NATURE")
    private String nature;

    @Column(name = "TP_SIGNAL")
    private String signal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return id.equals(that.id) && Objects.equals(description, that.description) && Objects.equals(nature, that.nature) && Objects.equals(signal, that.signal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, nature, signal);
    }
}
