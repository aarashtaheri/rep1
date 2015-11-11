package com.arash.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.LazyCollection;
import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "expense")
public class ExpenseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="calculation_period_id")
	private CalculationPeriodEntity calculationPeriod;
	
	@NotNull
	@Column(name="sum_expense")
	private double sumExpense;
	
	@Column(name="balance")
	private double balance;
	
	public ExpenseEntity() {}
	
	public ExpenseEntity(UserEntity user, CalculationPeriodEntity calculationPeriod) {
		this.user = user;
		this.calculationPeriod = calculationPeriod;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSumExpense() {
		return sumExpense;
	}

	public void setSumExpense(double sum_expense) {
		this.sumExpense = sum_expense;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public CalculationPeriodEntity getCalculationPeriod() {
		return calculationPeriod;
	}

	@JsonIgnore
	public UserEntity getUser() {
		return user;
	}

}
