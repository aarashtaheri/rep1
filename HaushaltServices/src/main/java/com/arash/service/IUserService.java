package com.arash.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.arash.model.ExpenseEntity;

@Service
public interface IUserService {
	
	Collection<ExpenseEntity> getExpenses(int userId, int calculationPeriod);

}
