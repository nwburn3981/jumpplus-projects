package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.ConnectionManager.ConnectionManager;
import com.cognixia.jump.Exceptions.RecordNotFoundException;
import com.cognixia.jump.model.Shoe;

public class ShoeDAO {

	private Connection conn = ConnectionManager.getConnection();

	public List<Shoe> findAll() {
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = "SLECT * FROM shoe";

		List<Shoe> shoes = new ArrayList<>();

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
				shoe.setPrice(rs.getFloat(5));
				shoe.setStock(rs.getInt(6));

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

	public boolean updateStock(Shoe shoe) {
		PreparedStatement prep = null;
		int numInserts = 0;
		String sql = "UPDATE shoe SET stock = ?, WHERE shoe_id = ?)";

		try {
			prep = conn.prepareStatement(sql);

			prep.setInt(1, shoe.getStock());
			prep.setInt(5, (int) shoe.getShoe_id());

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
