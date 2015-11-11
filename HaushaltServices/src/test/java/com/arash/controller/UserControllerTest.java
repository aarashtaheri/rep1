package com.arash.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.arash.Application;
import com.arash.ApplicationTest;
import com.arash.dao.CalculationPeriodDao;
import com.arash.dao.ExpenseDao;
import com.arash.dao.UserDao;
import com.arash.model.CalculationPeriodEntity;
import com.arash.model.ExpenseDTO;
import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;
import com.arash.service.IUserService;

import org.junit.Assert;
import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ApplicationTest.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserControllerTest {
	
	@Autowired UserDao userDao;
	@Autowired ExpenseDao expenseDao;
	@Autowired CalculationPeriodDao periodDao;
	
	
	@Autowired WebApplicationContext context;
	
	@Autowired IUserService userService;
	
	MockMvc mvc;
	
	UserEntity user1, user2;
	ExpenseEntity expense1, expense2;
	CalculationPeriodEntity calculationPeriod;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		user1 = new UserEntity("user1", "user1@email.com");
		userDao.save(user1);
		user2 = new UserEntity("user2", "user2@email.com");
		userDao.save(user2);
		
		calculationPeriod = new CalculationPeriodEntity(10, 2015);
		periodDao.save(calculationPeriod);
		
		expense1 = new ExpenseEntity(user1, calculationPeriod);
		expense2 = new ExpenseEntity(user2, calculationPeriod);
		expenseDao.save(expense1);
		expenseDao.save(expense2);
						
     }
	
	@Test
	public void testInsertedData() {
		Assert.assertEquals(user1, expense1.getUser());
		Assert.assertEquals(user2, expense2.getUser());
		
	}
	
	String server = "http://localhost:0/";

	@Test
	public void getOneUser_Test() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/1");
		ResultActions result = mvc.perform(requestBuilder);
		//if service available
		result.andExpect(MockMvcResultMatchers.status().isOk());
		
		
		//see the content of response
		String content = mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
		String expected = "{\"id\":1,\"email\":\"user1\",\"name\":\"user1@email.com\"}";
		Assert.assertEquals(expected, content);
		
		RequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/users/2");;
		String content2 = mvc.perform(requestBuilder2).andReturn().getResponse().getContentAsString();
		String expected2 = "{\"id\":2,\"email\":\"user2\",\"name\":\"user2@email.com\"}";
		Assert.assertEquals(expected2, content2);
		
		System.out.println(content);
		System.out.println(content2);
		
	}
	
	@Test
	public void getAllUsers_Test() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/all");
		ResultActions result = mvc.perform(requestBuilder);
		//if service available
		result.andExpect(MockMvcResultMatchers.status().isOk());
		
		//see the content of response
		String content = mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
		System.out.println(content);		
		
	}

//	@Test
//	public void getExpense() {
//        RestTemplate restTemplate = new RestTemplate();
//        ExpenseDTO expense = restTemplate.getForObject(server + "users/1/getExpense?periodId=1", ExpenseDTO.class);
//        Assert.assertNotNull(expense);
//	}
}
