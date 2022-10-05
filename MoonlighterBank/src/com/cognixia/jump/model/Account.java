package com.cognixia.jump.model;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private static enum Type {
		SAVINGS, CHECKING
	};

	private long id;

	private double balance;

	private LocalDateTime created;

	private int userId;

	private Type accountType;

	public Account() {
		this(0.0, LocalDateTime.now(), 0, Type.CHECKING);
		this.id = -1L;
	}

	public Account(double balance, LocalDateTime created, int userId, Type accountType) {
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
		balance = Math.round(balance * 100);
		return balance/100;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccountType() {
		return accountType.toString();
	}

	public void setAccountType(String accountType) {

		if (accountType.equals("CHECKING")) {
			this.accountType = Type.CHECKING;
		} else {
			this.accountType = Type.SAVINGS;
		}

	}

	@Override
	public String toString() {

		System.out.print(ansi().fgBrightBlue());
		return "Account: " + accountType + "-" + id + "\nBalance: " + balance + "\nOpened: " + created + "\nAccount Holder: "
				+ userId;
	}

}
