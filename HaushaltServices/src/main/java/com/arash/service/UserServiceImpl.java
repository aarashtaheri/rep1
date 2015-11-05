package com.arash.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.neo4j.cypher.internal.compiler.v2_1.ast.GreaterThan;
import org.neo4j.cypher.internal.compiler.v2_1.functions.Exp;
import org.neo4j.cypher.internal.compiler.v2_1.planner.logical.steps.selectCovered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arash.dao.UserDao;
import com.arash.model.ExpenseEntity;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public Collection<ExpenseEntity> getExpenses(int userId, int calculationPeriod) {
		
		List<ExpenseEntity> expenses = (List<ExpenseEntity>) userDao.findOne(userId).getExpenses();
		
		List<ExpenseEntity> ex = expenses.stream().filter(
				p -> p.getCalculationPeriod().getId() == calculationPeriod).collect(Collectors.toList());		
		return ex;
	}

}
