package ru.demi.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="finances_user")
public class User {

    public User(String firstName, String lastName, LocalDate birthDate, String emailAddress, String createdBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.createdBy = createdBy;
        this.lastUpdatedBy = createdBy;
        LocalDateTime now = LocalDateTime.now();
        lastUpdatedDate = now;
        createdDate = now;
    }

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long userId;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private LocalDate birthDate;

	@Column(nullable = false)
	private String emailAddress;

	@Column(nullable = false)
	private LocalDateTime lastUpdatedDate;

	@Column(nullable = false)
	private String lastUpdatedBy;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@Column(nullable = false, updatable = false)
	private String createdBy;
}
