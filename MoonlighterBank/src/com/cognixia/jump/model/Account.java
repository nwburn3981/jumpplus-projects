package com.cognixia.jump.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private static enum Type {
		SAVINGS, CHECKING
	};

	private long id;

	private double balance;

	private Timestamp created;

	private int userId;

	private Type accountType;
	
	public Account() {
		this(0.0, Timestamp.valueOf(LocalDateTime.now()), 0, Type.CHECKING);
		this.id = -1L;
	}

	public Account(double balance, Timestamp created, int userId, Type accountType) {
		super();
		this.id = -1L;
		this.balance = balance;
		this.created = created;
		this.userId = userId;
		this.accountType = accountType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Type getAccountType() {
		return accountType;
	}

	public void setAccountType(Type accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", created=" + created + ", userId=" + userId
				+ ", accountType=" + accountType + "]";
	}

}
