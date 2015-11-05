package com.arash.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	private String email;

	@NotNull
	private String name;

	@OneToMany(mappedBy="user")
	private Collection<ExpenseEntity> expenses;

	public UserEntity() {
	}

	public UserEntity(int id) {
		this.id = id;
	}

	public UserEntity(String email, String name) {
		this.email = email;
		this.name = name;
	}

	// Getter and setter methods

	public long getId() {
		return id;
	}

	public void setId(int value) {
		this.id = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public Collection<ExpenseEntity> getExpenses() {
		return expenses;
	}

}