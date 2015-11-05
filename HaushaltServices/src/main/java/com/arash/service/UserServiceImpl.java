package com.arash.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arash.dao.ExpenseDao;
import com.arash.dao.UserDao;
import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;
import com.google.common.collect.Iterators;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	ExpenseDao expenseDao;
	
	@Override
	public ExpenseEntity getExpenseByUserIdAndCalculationPeriod(int userId, int calculationPeriod) {
		
		List<ExpenseEntity> expenses = (List<ExpenseEntity>) userDao.findOne(userId).getExpenses();
		
		List<ExpenseEntity> ex = expenses.stream().filter(
				p -> p.getCalculationPeriod().getId() == calculationPeriod).collect(Collectors.toList());		
		return ex.get(0);
	}

	@Transactional
	@Override
	public UserEntity updateExpense(int userId, int calculationPeriod, float newValue) {
		// 1 . update the value in the database
		UserEntity user = userDao.findOne(userId);
		
		ExpenseEntity expense = getExpenseByUserIdAndCalculationPeriod(userId, calculationPeriod);
		expense.setSumExpense(newValue);
		expenseDao.save(expense);
				
		// 2. update the balance of all users according to the new expense
		float sumAllExpenses = 0;
		// TODO user lambda
		Iterable<UserEntity> users = userDao.findAll();
		for (UserEntity userEntity : users) {
			sumAllExpenses +=  getExpenseByUserIdAndCalculationPeriod(userEntity.getId(), calculationPeriod).getSumExpense();
		}
		
		int numberOfUsers =  Iterators.size( users.iterator());
		
		users = userDao.findAll();
		for (UserEntity userEntity : users) {
			expense = getExpenseByUserIdAndCalculationPeriod(userEntity.getId(), calculationPeriod);
			float balance = expense.getSumExpense() - (sumAllExpenses/numberOfUsers);
			expense.setBalance(balance);
			expenseDao.save(expense);
		}

		return user;
	}

}
