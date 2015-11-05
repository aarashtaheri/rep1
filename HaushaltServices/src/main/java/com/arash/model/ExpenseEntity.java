package com.arash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "expense")
public class ExpenseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	private int calculation_period_id;
	
	@NotNull
	@Column(name="sum_expense")
	private float sum_expense;
	
	@Null
	@Column(name="balance")
	private float balance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getCalculation_period_id() {
		return calculation_period_id;
	}

	public void setCalculation_period_id(int calculation_period_id) {
		this.calculation_period_id = calculation_period_id;
	}

	public float getSum_expense() {
		return sum_expense;
	}

	public void setSum_expense(float sum_expense) {
		this.sum_expense = sum_expense;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}
