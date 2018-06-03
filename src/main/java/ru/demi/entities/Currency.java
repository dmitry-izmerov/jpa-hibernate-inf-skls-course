package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Setter
@Entity
@IdClass(CurrencyId.class)
public class Currency {
    @Id
    private String name;
    @Id
    private String countryName;
    private String symbol;
}
