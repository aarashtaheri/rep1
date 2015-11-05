package com.arash.controller;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;
import com.arash.service.IUserService;
import com.arash.dao.UserDao;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	IUserService userService;
	
	@RequestMapping("/all")
	public Iterable<UserEntity> getAll() {
		return userDao.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody UserEntity get(@PathVariable int id) {  
		return userDao.findOne(id);
	}
	
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public @ResponseBody UserEntity updateExpense(@PathVariable int id, @RequestParam(value = "periodId") int periodId,  @RequestParam(value = "expense") float expense) {
		System.out.println(expense);
		
		return userService.updateExpense(id, periodId, expense);
		
	}
	
	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	public @ResponseBody UserEntity getByEmail(@PathVariable String email) {  
		return userDao.findByEmail(email);
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody ExpenseEntity getExpense() { 
		return userService.getExpenseByUserIdAndCalculationPeriod(1, 1);
//		return userDao.findOne(id).getExpenses();
	}
}
