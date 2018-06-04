package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Investment {
    protected String name;
    protected String issuer;
    protected LocalDateTime purchaseDate;
}
