package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class AccountType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long accountTypeId;

	@Column
	private String name;

	@Column
	private Date lastUpdatedDate;

	@Column
	private String lastUpdatedBy;

	@Column
	private Date createdDate;

	@Column
	private String createdBy;
}
