package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.ConnectionManager.ConnectionManager;
import com.cognixia.jump.Exceptions.RecordNotFoundException;
import com.cognixia.jump.model.Order;

public class OrderDAO {
	
	Connection conn = ConnectionManager.getConnection();
	
	//CREATE
	
	public boolean create(Order order) {
		
		PreparedStatement prep = null;
		String sql = "SELECT * FROM orders";
		
		int numInserts = 0;
		
		try {
			prep = conn.prepareStatement(sql);

			prep.setNull(1, Types.INTEGER);
			prep.setDate(2, order.getOrder_date());
			prep.setInt(3, (int) order.getUser_id());
			prep.setFloat(3, order.getTotal());

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("Order " + order.getOrder_id() + " placed.");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;

	}
	
	//FINDBYUSERID
	public List<Order> findOrdersByUser(int id) {
		
		List<Order> orders = new ArrayList<>();
		PreparedStatement prep = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM orders WHERE user_id = ?";
		
		try {
			
			prep = conn.prepareStatement(sql);
			
			prep.setInt(1, id);
			
			rs = prep.executeQuery();
			
			while(rs.next()) {
				if(rs.getRow() == 0) {
					throw new RecordNotFoundException("No orders found");
				}
				
				Order order = new Order();
				order.setOrder_id(rs.getInt(1));
				order.setOrder_date(rs.getDate(2));
				order.setUser_id(rs.getInt(3));
				order.setTotal(rs.getFloat(4));
				
				orders.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			System.out.println(e);
		}
		
		return orders;
	}

}
