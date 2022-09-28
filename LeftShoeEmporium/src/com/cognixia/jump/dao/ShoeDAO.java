package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cognixia.jump.ConnectionManager.ConnectionManager;
import com.cognixia.jump.Exceptions.RecordNotFoundException;
import com.cognixia.jump.model.Shoe;

public class ShoeDAO {

	private Connection conn = ConnectionManager.getConnection();

	public ArrayList<Shoe> findAll() {
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM shoe";

		ArrayList<Shoe> shoes = new ArrayList<>();

		try {

			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
					throw new RecordNotFoundException("No shoes found");
				}

				Shoe shoe = new Shoe();
				shoe.setShoe_id(rs.getInt(1));
				shoe.setBrand(rs.getString(2));
				shoe.setShoe_name(rs.getString(3));
				shoe.setShoe_type(rs.getString(4));
				shoe.setShoe_code(rs.getString(5));
				shoe.setPrice(rs.getFloat(6));
				shoe.setStock(rs.getInt(7));

				shoes.add(shoe);
			}

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

		return shoes;
	}
	
	public Shoe findByShoeCode(String code) {
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM shoe WHERE shoe_code = ?";
		
		Shoe shoe = new Shoe();
		
		try {

			prep = conn.prepareStatement(sql);
			prep.setString(1, code);
			
			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
					throw new RecordNotFoundException("No shoes found");
				}

				
				shoe.setShoe_id(rs.getInt(1));
				shoe.setBrand(rs.getString(2));
				shoe.setShoe_name(rs.getString(3));
				shoe.setShoe_type(rs.getString(4));
				shoe.setShoe_code(rs.getString(5));
				shoe.setPrice(rs.getFloat(6));
				shoe.setStock(rs.getInt(7));

			}

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
		
		
		return shoe;
	}

	public boolean updateStock(Shoe shoe) {
		PreparedStatement prep = null;
		int numInserts = 0;
		String sql = "UPDATE shoe SET stock = ?, WHERE shoe_id = ?)";

		try {
			prep = conn.prepareStatement(sql);

			prep.setInt(1, shoe.getStock());
			prep.setInt(2, (int) shoe.getShoe_id());

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
