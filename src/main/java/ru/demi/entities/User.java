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

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private LocalDate birthDate;

	@Column
	private String emailAddress;

	@Column
	private LocalDateTime lastUpdatedDate;

	@Column
	private String lastUpdatedBy;

	@Column
	private LocalDateTime createdDate;

	@Column
	private String createdBy;
}
