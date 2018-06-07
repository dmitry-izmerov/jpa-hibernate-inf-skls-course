package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Enumerated(EnumType.STRING)
    @Column
	private AccountType accountType;

	private String name;

	private BigDecimal initialBalance;

	private BigDecimal currentBalance;

	private Date openDate;

	private Date closeDate;

	private Date lastUpdatedDate;

	private String lastUpdatedBy;

	private Date createdDate;

	private String createdBy;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account", orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_account", joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<>();
}
