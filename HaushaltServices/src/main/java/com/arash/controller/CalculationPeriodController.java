package com.arash.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arash.model.CalculationPeriodEntity;
import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;
import com.arash.dao.CalculationPeriodDao;
import com.arash.dao.UserDao;

@RestController
@RequestMapping("/calc")
public class CalculationPeriodController {
	@Autowired
	CalculationPeriodDao calcDao;
	
	@RequestMapping("/all")
	public Iterable<CalculationPeriodEntity> getAll() {
		return calcDao.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody CalculationPeriodEntity get(@PathVariable Integer id) {  
		return calcDao.findOne(id);
	}
	
//	@RequestMapping(value = "/{id}/expenses", method = RequestMethod.GET)
//	public @ResponseBody Set<ExpenseEntity> getExpenses(@PathVariable Integer id) {  
//		return calcDao.findOne(id).ge
//	}
}
