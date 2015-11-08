package com.arash.dao;

import com.arash.model.CalculationPeriodEntity;
import com.arash.model.ExpenseEntity;

public abstract class ExpenseDaoImp implements ExpenseDao {

	@Override
	public Iterable<ExpenseEntity> findByCalculationPeriod(CalculationPeriodEntity period) {
		return findAll();
	}
}
