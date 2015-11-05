package com.arash.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "calculation_period")
public class CalculationPeriodEntity {

	@Id
	private int id;
	
	@Column(name="month")
	private int month;
	
	@Column(name="year")
	private int year;
	
//	@OneToMany(mappedBy="calculation_period")
//	private Set<ExpenseEntity> expenses;
//	public Set<ExpenseEntity> getExpenses() {
//		return expenses;
//	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}



}
