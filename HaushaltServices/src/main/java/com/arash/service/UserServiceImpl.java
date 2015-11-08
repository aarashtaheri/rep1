package com.arash.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arash.dao.ExpenseDao;
import com.arash.dao.UserDao;
import com.arash.model.CalculationPeriodEntity;
import com.arash.model.ExpenseDTO;
import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	ExpenseDao expenseDao;
	
	@Override
	public ExpenseEntity getExpenseByUserIdAndCalculationPeriod(int userId, int calculationPeriod) {

		
//		@SuppressWarnings("unused")
//		Iterable<ExpenseEntity> espensesForPeriod = expenseDao.findByCalculationPeriod(new CalculationPeriodEntity(11,2105));

		
		List<ExpenseEntity> expenses = (List<ExpenseEntity>) userDao.findOne(userId).getExpenses();
			
		ExpenseEntity exp = expenses.stream()
				 .filter(p -> p.getCalculationPeriod().getId() == calculationPeriod)
				 .findFirst().get();
		 
		return exp;
	}
	
	public ExpenseDTO getExpenseByUserIdAndCalculationPeriod2(int userId, int calculationPeriod) {
		
		List<ExpenseEntity> expenses = (List<ExpenseEntity>) userDao.findOne(userId).getExpenses();
		
		// find element in list using lambda
		ExpenseEntity exp = expenses.stream()
				 .filter(p -> p.getCalculationPeriod().getId() == calculationPeriod)
				 .findFirst().get();
		
		ExpenseDTO expenseDTO = new ExpenseDTO();
		
		expenseDTO.setUserId(userId);
		
		expenseDTO.setMyBalance(exp.getBalance());
		expenseDTO.setSumMyExpenses(exp.getSumExpense());
		

		List<ExpenseEntity> expensesAll = Lists.newArrayList( expenseDao.findAll());
		
		//filter elements in a list using lambda
		List<ExpenseEntity> expensesForPeriod = expensesAll.stream().
		filter(e -> e.getCalculationPeriod().getId() == calculationPeriod).collect(Collectors.toList());
		
		//sum using lambda
		double sumExpsensesInPeriod = expensesAll.stream().
				filter(e -> e.getCalculationPeriod().getId() == calculationPeriod)
				.collect(Collectors.summarizingDouble(ExpenseEntity::getSumExpense)).getSum();
		
		expenseDTO.setSumEspensesOfAllUsers((float) sumExpsensesInPeriod);
		expenseDTO.setEachOneShouldPay((float) (sumExpsensesInPeriod/3));
		
		System.out.println(sumExpsensesInPeriod);
		 
		return expenseDTO;
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
		// TODO use lambda
		Collection<UserEntity> users = (Collection<UserEntity>) userDao.findAll();
		for (UserEntity userEntity : users) {
			sumAllExpenses +=  getExpenseByUserIdAndCalculationPeriod(userEntity.getId(), calculationPeriod).getSumExpense();
		}

		int numberOfUsers =  Iterators.size(users.iterator());
		
		for (UserEntity userEntity : users) {
			expense = getExpenseByUserIdAndCalculationPeriod(userEntity.getId(), calculationPeriod);
			float balance = expense.getSumExpense() - (sumAllExpenses/numberOfUsers);
			expense.setBalance(balance);
			expenseDao.save(expense);
		}

		return user;
	}

}
