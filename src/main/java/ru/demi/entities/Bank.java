package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bankId;

	private String name;

	@Embedded
	private Address address;

    private Date lastUpdatedDate;

	private String lastUpdatedBy;

	private Date createdDate;

	private String createdBy;

    private boolean isInternational;

    @ElementCollection
    @CollectionTable(name = "bank_contact", joinColumns = @JoinColumn(name = "bank_id"))
    @Column(name = "name")
    private List<String> contacts = new ArrayList<>();
}
