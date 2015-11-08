package com.arash.dao;

import org.springframework.data.repository.CrudRepository;

import com.arash.model.CalculationPeriodEntity;
import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;

public interface ExpenseDao extends CrudRepository<ExpenseEntity, Integer> {
	
	public ExpenseEntity findByUser(UserEntity user);
	
	public Iterable<ExpenseEntity> findByCalculationPeriod(CalculationPeriodEntity period);
}
