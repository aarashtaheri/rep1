package com.arash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arash.model.ExpenseEntity;
import com.arash.dao.ExpenseDao;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
	@Autowired
	ExpenseDao expenseDao;
	
	@RequestMapping("/all")
	public Iterable<ExpenseEntity> getAll() {
		return expenseDao.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ExpenseEntity getOne(@PathVariable Integer id) {  
		return expenseDao.findOne(id);
	}
	
//	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
//	public @ResponseBody Iterable<ExpenseEntity> getUserExpenses(@PathVariable Integer userId) {  
//		return expenseDao.findAll()getClass();
//	}
}
