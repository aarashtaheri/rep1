package com.arash.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.arash.Application;
import com.arash.dao.UserDao;
import com.arash.model.ExpenseDTO;
import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;
import com.google.common.collect.Iterators;

import org.junit.Assert;
import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserControllerTest {
	
	@Autowired
	UserDao userDao;
	

	@Before
	public void setup() {
		UserEntity user1 = new UserEntity("user1", "user1@email.com");
		userDao.save(user1);
		System.out.println("created user id= " + user1.getId());
     }
	
	String server = "http://localhost:0/";

	//TODO use mock rest server
	@Test
	public void getOneUsers() {
//		RestTemplate restTemplate = new RestTemplate();
//
//        UserEntity user= restTemplate.getForObject(server + "users/1", UserEntity.class);
//        Assert.assertEquals("user1", user.getName());
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
