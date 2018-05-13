package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;

//	private Bank bank;

//	private AccountType accountType;

	private String name;

	private BigDecimal initialBalance;

	private BigDecimal currentBalance;

	private Date openDate;

	private Date closeDate;

	private Date lastUpdatedDate;

	private String lastUpdatedBy;

	private Date createdDate;

	private String createdBy;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private List<Transaction> transactions = new ArrayList<>();
}
