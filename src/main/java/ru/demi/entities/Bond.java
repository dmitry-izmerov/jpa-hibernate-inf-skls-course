package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Bond extends Investment {
    private BigDecimal value;
    private BigDecimal interestRate;
    private LocalDateTime maturityDate;
}
