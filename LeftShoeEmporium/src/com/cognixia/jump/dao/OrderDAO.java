package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.cognixia.jump.ConnectionManager.ConnectionManager;
import com.cognixia.jump.Exceptions.RecordNotFoundException;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.Shoe;

public class OrderDAO {

	Connection conn = ConnectionManager.getConnection();

	// CREATE, create then returned created order for updated id

	public Order create(Order order) {

		PreparedStatement prep = null;

		String sql = "INSERT INTO orders  \r\n" + "VALUES\r\n" + "(?, \r\n" + "?, \r\n" + "?,\r\n" + "?\r\n" + ")";

		int numInserts = 0;

		try {
			prep = conn.prepareStatement(sql);

			prep.setNull(1, Types.INTEGER);
			prep.setDate(2, order.getOrder_date());
			prep.setInt(3, (int) order.getUser_id());
			prep.setDouble(4, order.getTotal());

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("Order placed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		prep = null;
		ResultSet rs = null;

		String sql2 = "SELECT order_id FROM orders WHERE user_id = ? ORDER BY order_id DESC";

		// fix id stuff here
		try {
			prep = conn.prepareStatement(sql2);

			prep.setInt(1, (int) order.getUser_id());

			rs = prep.executeQuery();

			rs.next();
			if (rs.getRow() == 0) {
				throw new RecordNotFoundException("No orders found");
			}

			order.setOrder_id(rs.getInt(1));
			System.out.println("This should be 3" + order.getOrder_id());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			System.out.println(e);
		}

		try {
			prep.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return order;
	}

	// Will create relationship between order and its shoes in the DB
	public boolean createRelationship(int orderID, int shoeID) {

		PreparedStatement prep = null;
		String sql = "INSERT INTO orders_shoes  \r\n" + "VALUES\r\n" + "(?, \r\n" + "?);";///// start here

		int numInserts = 0;

		try {
			prep = conn.prepareStatement(sql);

			prep.setInt(1, orderID);
			prep.setInt(2, shoeID);

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("\nSuccess");
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

	// FINDBYUSERID
	public ArrayList<Order> findOrdersByUser(int id) {

		ArrayList<Order> orders = new ArrayList<>();
		PreparedStatement prep = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM orders WHERE user_id = ?";

		try {

			prep = conn.prepareStatement(sql);

			prep.setInt(1, id);

			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
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

	// Will find the Shoe information for each shoe on the specified order.
	public ArrayList<Shoe> findShoesOnOrder(int id) {

		ArrayList<Shoe> shoes = new ArrayList<>();
		PreparedStatement prep = null;
		ResultSet rs = null;

		String sql = "SELECT brand, shoe_name, shoe_type, price FROM orders \r\n" + "JOIN orders_shoes \r\n"
				+ "ON orders.order_id = orders_shoes.order_id\r\n" + "JOIN shoe\r\n"
				+ "ON orders_shoes.shoe_id = shoe.shoe_id\r\n" + "WHERE orders.order_id = ? ";

		try {

			prep = conn.prepareStatement(sql);

			prep.setInt(1, id);

			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
					throw new RecordNotFoundException("No orders found");
				}

				Shoe shoe = new Shoe();
				shoe.setBrand(rs.getString(1));
				shoe.setShoe_name(rs.getString(2));
				shoe.setShoe_type(rs.getString(3));
				shoe.setPrice(rs.getFloat(4));

				shoes.add(shoe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			System.out.println(e);
		}

		return shoes;

	}

}
