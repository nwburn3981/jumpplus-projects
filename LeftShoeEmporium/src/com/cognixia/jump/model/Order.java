package com.cognixia.jump.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private long order_id;

	private Date order_date;

	private long user_id;

	private float total;

	private Cart cart;

	public Order() {
		this(Date.valueOf(LocalDate.now()), -1L, 0f, new Cart());
		this.order_id = -1L;
	}

	public Order(Date order_date, long user_id, float total, Cart cart) {
		super();
		this.order_id = -1L;
		this.order_date = order_date;
		this.user_id = user_id;
		this.total = total;
		this.cart = cart;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_date=" + order_date + ", user_id=" + user_id + ", total="
				+ total + ", cart=" + cart + "]";
	}

}
