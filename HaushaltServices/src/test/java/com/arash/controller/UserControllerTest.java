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
import com.arash.dao.UserDao;
import com.arash.model.ExpenseDTO;
import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;
import com.google.common.collect.Iterators;

import net.wimpi.telnetd.io.terminal.ansi;

import org.junit.Assert;
import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserControllerTest {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	WebApplicationContext context;
	
	MockMvc mvc;
	

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		UserEntity user1 = new UserEntity("user1", "user1@email.com");
		userDao.save(user1);
		System.out.println("created user id= " + user1.getId());
     }
	
	String server = "http://localhost:0/";

	//TODO use mock rest server
	@Test
	public void getOneUsers() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/1");
		ResultActions result = mvc.perform(requestBuilder);
		result.andExpect(MockMvcResultMatchers.status().isOk());
		
		
		
	}
	
//	@Test
//	public void getAllUsers() {
//        RestTemplate restTemplate = new RestTemplate();
//        Iterable<UserEntity> users= (Iterable<UserEntity>) restTemplate.getForObject(server + "users/all", Object.class);
//        
//        Assert.assertEquals(3 ,Iterators.size(users.iterator()));
////        Assert.assertEquals("arash", users.iterator().next().getName());
//	}
//	
//	@Test
//	public void getExpense() {
//        RestTemplate restTemplate = new RestTemplate();
//        ExpenseDTO expense = restTemplate.getForObject(server + "users/1/getExpense?periodId=1", ExpenseDTO.class);
//        Assert.assertNotNull(expense);
//	}
}
