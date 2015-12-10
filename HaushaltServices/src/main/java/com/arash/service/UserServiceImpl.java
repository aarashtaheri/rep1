package com.arash.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arash.dao.CalculationPeriodDao;
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

	@Autowired
	CalculationPeriodDao periodDao;

	private int findUserId(String username) {
		UserEntity user = userDao.findByName(username);

		int userId;
		if (user == null) {
			throw new RuntimeException("user with name [" + username + "] not found");
		} else {
			userId = user.getId();
		}
		return userId;
	}

	@Override
	public ExpenseEntity getExpenseByUserIdAndCalculationPeriod(int userId, int calculationPeriod) {

		// @SuppressWarnings("unused")
		// Iterable<ExpenseEntity> espensesForPeriod =
		// expenseDao.findByCalculationPeriod(new
		// CalculationPeriodEntity(11,2105));

		final List<ExpenseEntity> expenses = (List<ExpenseEntity>) userDao.findOne(userId).getExpenses();

		final ExpenseEntity exp = expenses.stream().filter(p -> p.getCalculationPeriod().getId() == calculationPeriod)
				.findFirst().get();

		return exp;
	}

	@Override
	public ExpenseDTO getExpenseByUserIdAndCalculationPeriod2(String username, int calculationPeriod) {

		int userId = findUserId(username);

		final List<ExpenseEntity> expenses = (List<ExpenseEntity>) userDao.findOne(userId).getExpenses();

		// find element in list using lambda
		final ExpenseEntity exp = expenses.stream().filter(p -> p.getCalculationPeriod().getId() == calculationPeriod)
				.findFirst().get();

		final ExpenseDTO expenseDTO = new ExpenseDTO();

		expenseDTO.setUserId(userId);

		expenseDTO.setMyBalance(exp.getBalance());
		expenseDTO.setSumMyExpenses(exp.getSumExpense());

		final List<ExpenseEntity> expensesAll = Lists.newArrayList(expenseDao.findAll());

		// filter elements in a list using lambda
		final List<ExpenseEntity> expensesForPeriod = expensesAll.stream()
				.filter(e -> e.getCalculationPeriod().getId() == calculationPeriod).collect(Collectors.toList());

		// sum using lambda
		final double sumExpsensesInPeriod = expensesAll.stream()
				.filter(e -> e.getCalculationPeriod().getId() == calculationPeriod)
				.collect(Collectors.summarizingDouble(ExpenseEntity::getSumExpense)).getSum();

		expenseDTO.setSumEspensesOfAllUsers((float) sumExpsensesInPeriod);
		expenseDTO.setEachOneShouldPay((float) (sumExpsensesInPeriod / 3));

		System.out.println(sumExpsensesInPeriod);

		return expenseDTO;
	}

	@Override
	public void initDB() {
		// only execute if the database is empty
		if (userDao.findAll().iterator().hasNext()) {
			return;
		}

		final UserEntity arash = new UserEntity("arash", "");
		userDao.save(arash);

		final UserEntity omid = new UserEntity("omid", "");
		userDao.save(omid);

		final UserEntity afsane = new UserEntity("afsane", "");
		userDao.save(afsane);

		final CalculationPeriodEntity c1 = new CalculationPeriodEntity(11, 2015);
		final CalculationPeriodEntity c2 = new CalculationPeriodEntity(12, 2015);
		final CalculationPeriodEntity c3 = new CalculationPeriodEntity(1, 2016);
		final CalculationPeriodEntity c4 = new CalculationPeriodEntity(2, 2016);
		final CalculationPeriodEntity c5 = new CalculationPeriodEntity(3, 2016);
		final CalculationPeriodEntity c6 = new CalculationPeriodEntity(4, 2016);

		periodDao.save(c1);
		periodDao.save(c2);
		periodDao.save(c3);
		periodDao.save(c4);
		periodDao.save(c5);
		periodDao.save(c6);

		final ExpenseEntity exp1_arash = new ExpenseEntity(arash, c1);
		final ExpenseEntity exp1_omid = new ExpenseEntity(omid, c1);
		final ExpenseEntity exp1_afsane = new ExpenseEntity(afsane, c1);
		final ExpenseEntity exp2_arash = new ExpenseEntity(arash, c2);
		final ExpenseEntity exp2_omid = new ExpenseEntity(omid, c2);
		final ExpenseEntity exp2_afsane = new ExpenseEntity(afsane, c2);
		expenseDao.save(exp1_arash);
		expenseDao.save(exp1_omid);
		expenseDao.save(exp1_afsane);
		expenseDao.save(exp2_arash);
		expenseDao.save(exp2_omid);
		expenseDao.save(exp2_afsane);

	}

	@Transactional
	@Override
	public UserEntity updateExpense(String username, int calculationPeriod, float newValue) {

		int userId = findUserId(username);

		// 1 . update the value in the database
		final UserEntity user = userDao.findOne(userId);

		ExpenseEntity expense = getExpenseByUserIdAndCalculationPeriod(userId, calculationPeriod);
		expense.setSumExpense(newValue);
		expenseDao.save(expense);

		// 2. update the balance of all users according to the new expense
		float sumAllExpenses = 0;
		final Collection<UserEntity> users = (Collection<UserEntity>) userDao.findAll();
		for (final UserEntity userEntity : users) {
			sumAllExpenses += getExpenseByUserIdAndCalculationPeriod(userEntity.getId(), calculationPeriod)
					.getSumExpense();
		}

		final int numberOfUsers = Iterators.size(users.iterator());

		for (final UserEntity userEntity : users) {
			expense = getExpenseByUserIdAndCalculationPeriod(userEntity.getId(), calculationPeriod);
			final double balance = expense.getSumExpense() - (sumAllExpenses / numberOfUsers);
			expense.setBalance(balance);
			expenseDao.save(expense);
		}

		return user;
	}

}
