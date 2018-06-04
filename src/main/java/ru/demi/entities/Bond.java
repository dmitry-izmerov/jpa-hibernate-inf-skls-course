package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@DiscriminatorValue("bonds")
@Entity
public class Bond extends Investment {
    private BigDecimal value;
    private BigDecimal interestRate;
    private LocalDateTime maturityDate;
}
