package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long budgetId;

    private String name;

    private BigDecimal goalAmount;

    private String period;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "budget_transaction", joinColumns = @JoinColumn(name = "budget_id"),
        inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private List<Transaction> transactions = new ArrayList<>();
}
