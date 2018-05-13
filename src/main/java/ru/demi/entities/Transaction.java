package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

//	private Account account;

    @Enumerated(value = EnumType.STRING)
	private TransactionType transactionType;

    private String title;

	private BigDecimal amount;

	private BigDecimal initialBalance;

	private BigDecimal closingBalance;

	private String notes;

	private Date lastUpdatedDate;

	private String lastUpdatedBy;

	private Date createdDate;

	private String createdBy;
}
