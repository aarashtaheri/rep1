package com.arash.model;

public class ExpenseDTO {
	
	private int userId;
	private double sumMyExpenses;
	private double sumEspensesOfAllUsers;
	private double eachOneShouldPay;
	private double myBalance;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getSumMyExpenses() {
		return sumMyExpenses;
	}
	public void setSumMyExpenses(double sumMyExpenses) {
		this.sumMyExpenses = sumMyExpenses;
	}
	public double getSumEspensesOfAllUsers() {
		return sumEspensesOfAllUsers;
	}
	public void setSumEspensesOfAllUsers(double sumEspensesOfAllUsers) {
		this.sumEspensesOfAllUsers = sumEspensesOfAllUsers;
	}
	public double getEachOneShouldPay() {
		return eachOneShouldPay;
	}
	public void setEachOneShouldPay(double eachOneShouldPay) {
		this.eachOneShouldPay = eachOneShouldPay;
	}
	public double getMyBalance() {
		return myBalance;
	}
	public void setMyBalance(double myBalance) {
		this.myBalance = myBalance;
	}
	
	
}
