package com.cognixia.jump.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private long order_id;

	private LocalDate order_date;

	private long user_id;
	
	public Order() {
		this(LocalDate.now(), -1L);
		this.order_id = -1L;
	}

	public Order(LocalDate order_date, long user_id) {
		super();
		this.order_id = -1L;
		this.order_date = order_date;
		this.user_id = user_id;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public LocalDate getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_date=" + order_date + ", user_id=" + user_id + "]";
	}

}
