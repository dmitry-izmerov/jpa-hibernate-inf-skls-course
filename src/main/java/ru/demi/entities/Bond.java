package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Bond extends Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bondId;
    private BigDecimal value;
    private BigDecimal interestRate;
    private LocalDateTime maturityDate;
}
