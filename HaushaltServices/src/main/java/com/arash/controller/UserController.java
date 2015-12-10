package com.arash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arash.dao.UserDao;
import com.arash.model.ExpenseDTO;
import com.arash.model.UserEntity;
import com.arash.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserDao userDao;

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody UserEntity get(@PathVariable int id) {
		return userDao.findOne(id);
	}

	@RequestMapping("/all")
	public Iterable<UserEntity> getAll() {
		return userDao.findAll();
	}

	@RequestMapping(value = "/getByEmail/{email}", method = RequestMethod.GET)
	public @ResponseBody UserEntity getByEmail(@PathVariable String email) {
		return userDao.findByEmail(email);
	}

	@RequestMapping(value = "/{username}/getExpense", method = RequestMethod.GET)
	public @ResponseBody ExpenseDTO getExpenseByUserName(@PathVariable String username,
			@RequestParam(value = "periodId") int periodId) {
		return userService.getExpenseByUserIdAndCalculationPeriod2(username, periodId);
	}

	@RequestMapping(value = "/initdb", method = RequestMethod.GET)
	public void initDB() {
		userService.initDB();
	}

	@RequestMapping(value = "/{username}/updateExpense", method = RequestMethod.GET)
	public @ResponseBody UserEntity updateExpense(@PathVariable String username,
			@RequestParam(value = "periodId") int periodId, @RequestParam(value = "expense") float expense) {
		return userService.updateExpense(username, periodId, expense);
	}

	// @RequestMapping("/questions")
	// public Iterable<QuestionEntity> getQuestions(
	// @RequestParam(value = "countryId", required = false, defaultValue = "0")
	// Integer countryId,
	// @RequestParam(value = "catId", required = false) Integer catId,
	// @RequestParam(value = "lang", required = false) String langCode) {
	// return questionService.findByCountryAndCategoryAndLanguage(countryId,
	// catId, langCode);
	// }
	// @RequestMapping(value = "/test", method = RequestMethod.GET)
	// public @ResponseBody ExpenseDTO test() {
	// return userService.getExpenseByUserIdAndCalculationPeriod2(1, 1);
	// }
}
