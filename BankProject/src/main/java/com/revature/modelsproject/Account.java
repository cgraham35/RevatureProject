package com.revature.modelsproject;

import com.revature.enums.AccountStatus;
import com.revature.enums.AccountType;

public class Account {
	  private int accountId; // primary key
	  private int accountNo;
	  private double balance;  // not null
	  private String firstName;
	  private String lastName;
	  private AccountStatus status;
	  private AccountType type;
	
	
	
	public Account() {
		
	}

	public int getAccountNo() {
		return accountNo;
	}

	
	// we create getter and setter methods to define properties
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", firstName=" + firstName + ", lastName=" + lastName + ", balance="
				+ balance + "]";
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	
	
}
