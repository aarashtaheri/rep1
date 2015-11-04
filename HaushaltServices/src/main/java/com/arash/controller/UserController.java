package com.arash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arash.model.User;
import com.arash.dao.UserDao;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserDao userDao;
	
	@RequestMapping("/all")
	public Iterable<User> getAllQuestions() {
		return userDao.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable Long id) {  
		return userDao.findOne(id);
	}
}
