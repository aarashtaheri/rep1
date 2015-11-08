package com.arash.model;

public class ExpenseDTO {
	
	private int userId;
	private float sumMyExpenses;
	private float sumEspensesOfAllUsers;
	private float eachOneShouldPay;
	private float myBalance;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public float getSumMyExpenses() {
		return sumMyExpenses;
	}
	public void setSumMyExpenses(float sumMyExpenses) {
		this.sumMyExpenses = sumMyExpenses;
	}
	public float getSumEspensesOfAllUsers() {
		return sumEspensesOfAllUsers;
	}
	public void setSumEspensesOfAllUsers(float sumEspensesOfAllUsers) {
		this.sumEspensesOfAllUsers = sumEspensesOfAllUsers;
	}
	public float getEachOneShouldPay() {
		return eachOneShouldPay;
	}
	public void setEachOneShouldPay(float eachOneShouldPay) {
		this.eachOneShouldPay = eachOneShouldPay;
	}
	public float getMyBalance() {
		return myBalance;
	}
	public void setMyBalance(float myBalance) {
		this.myBalance = myBalance;
	}
	
	
}
