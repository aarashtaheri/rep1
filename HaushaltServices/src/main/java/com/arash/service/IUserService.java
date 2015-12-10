package com.arash.service;

import org.springframework.stereotype.Service;

import com.arash.model.ExpenseDTO;
import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;

@Service
public interface IUserService {

	public ExpenseEntity getExpenseByUserIdAndCalculationPeriod(int userId, int calculationPeriod);

	public ExpenseDTO getExpenseByUserIdAndCalculationPeriod2(int userId, int calculationPeriod);

	public ExpenseDTO getExpenseByUserIdAndCalculationPeriod2(String username, int calculationPeriod);

	public void initDB();

	public UserEntity updateExpense(int userId, int calcPeriodId, float newValue);
}
