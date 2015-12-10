package com.arash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calculation_period")
public class CalculationPeriodEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "month")
	private int month;

	@Column(name = "year")
	private int year;

	@Column
	private boolean processed;

	// @OneToMany(mappedBy="calculation_period")
	// private Set<ExpenseEntity> expenses;
	// public Set<ExpenseEntity> getExpenses() {
	// return expenses;
	// }

	public CalculationPeriodEntity() {
	}

	public CalculationPeriodEntity(int month, int year) {
		this.month = month;
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
