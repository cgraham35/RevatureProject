package com.revature.daosproject;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, I extends Serializable> {

	// CREATE this interface first in your projects.
	// REMEMBER, we use interfaces to create abstract methods that's shared across classes
	// a class can implement many interfaces, but it can only extends ONE class.
	//
	// As we extend Serializable, DAO is now a "factory" class.
	List<T> findAll();
	T findbyId(I id);
	T create(T obj);
	T update(T obj);
	
	// optional to create a T delete (T obj)
	
	/*
	 * Adding default methods so that we can instantiate DAO concrete classes with a
	 * REFERENCE to the DAO interface and override these methods;
	 */
	
	
	
}
