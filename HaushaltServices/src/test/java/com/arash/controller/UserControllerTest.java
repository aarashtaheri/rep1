package com.arash.controller;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.arash.model.ExpenseEntity;
import com.arash.model.UserEntity;
import com.google.common.collect.Iterators;

import junit.framework.Assert;

public class UserControllerTest {
	String server = "http://localhost:8080/";

	//TODO use mock rest server
	@Test
	public void getOneUsers() {
        RestTemplate restTemplate = new RestTemplate();
        UserEntity user= restTemplate.getForObject(server + "users/1", UserEntity.class);
        Assert.assertEquals("arash", user.getName());
	}
	
	@Test
	public void getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        Iterable<UserEntity> users= (Iterable<UserEntity>) restTemplate.getForObject(server + "users/all", Object.class);
        
        Assert.assertEquals(3 ,Iterators.size(users.iterator()));
//        Assert.assertEquals("arash", users.iterator().next().getName());
	}
	
	@Test
	public void getExpense() {
        RestTemplate restTemplate = new RestTemplate();
        ExpenseEntity expense= restTemplate.getForObject(server + "users/1/getExpense?periodId=1", ExpenseEntity.class);
        Assert.assertEquals(11, expense.getCalculationPeriod().getMonth());
	}
}
