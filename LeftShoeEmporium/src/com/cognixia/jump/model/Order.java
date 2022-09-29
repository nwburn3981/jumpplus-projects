package com.cognixia.jump.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.cognixia.jump.dao.OrderDAO;
import com.cognixia.jump.dao.UserDAO;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private long order_id;

	private Date order_date;

	private long user_id;

	private double total;

	public Order() {
		this(Date.valueOf(LocalDate.now()), -1L, 0f);
		this.order_id = -1L;
	}

	public Order(Date order_date, long user_id, double total) {
		super();
		this.order_id = -1L;
		this.order_date = order_date;
		this.user_id = user_id;
		this.total = total;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public void generateInvoice(Order order) {
		OrderDAO orderDAO = new OrderDAO();
		UserDAO userDAO = new UserDAO();
		
		User orderUser = userDAO.findUserById((int) order.getUser_id());
		
		ArrayList<Shoe> shoes = orderDAO.findShoesOnOrder((int) order.getOrder_id());

		System.out.print("Customer Name: " + orderUser.getFirst_name() + " " + orderUser.getLast_name() + "       " + "Date: " + order.getOrder_date()
		+ "\nInvoice No: " + order.getOrder_id()
		+ "\n\t" + "        Item                Price            Stock     Item Code");
		
		for (Shoe shoe : shoes) {
			System.out.println("\n\t" + shoe);
		}
		
		System.out.println("\n\n\tTotal = $" + order.getTotal());
		
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_date=" + order_date + ", user_id=" + user_id + ", total="
				+ total + "]";
	}

}
