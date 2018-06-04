package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Stock extends Investment {
    private BigDecimal sharePrice;
    private Long quantity;
}
