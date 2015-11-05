package com.arash.dao;

import org.springframework.data.repository.CrudRepository;

import com.arash.model.ExpenseEntity;

public interface ExpenseDao extends CrudRepository<ExpenseEntity, Integer> {

}
