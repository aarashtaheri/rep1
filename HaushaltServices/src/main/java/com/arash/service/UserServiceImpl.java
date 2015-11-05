package com.arash.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.neo4j.cypher.internal.compiler.v2_1.ast.GreaterThan;
import org.neo4j.cypher.internal.compiler.v2_1.functions.Exp;
import org.neo4j.cypher.internal.compiler.v2_1.planner.logical.steps.selectCovered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arash.dao.ExpenseDao;
import com.arash.dao.UserDao;
import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;

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
		
		
		return user;
	}

}
