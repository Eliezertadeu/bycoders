package com.bycorders.finance.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_OWNER")
public class Owner implements Serializable {

    public Owner() {}

    public Owner(String name) {
        this.name = name;
    }

    private static final Long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OW_ID")
    private Long id;

    @Column(name = "OW_NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return name.equals(owner.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
