package com.arash.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;
import com.arash.dao.UserDao;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserDao userDao;
	
	@RequestMapping("/all")
	public Iterable<UserEntity> getAll() {
		return userDao.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody UserEntity get(@PathVariable Long id) {  
		return userDao.findOne(id);
	}
	
//	@RequestMapping(value = "/{id}/expenses", method = RequestMethod.GET)
//	public @ResponseBody Set<ExpenseEntity> getExpenses(@PathVariable Integer id) {  
//		return userDao.findOne(id).getExpenses();
//	}
}
