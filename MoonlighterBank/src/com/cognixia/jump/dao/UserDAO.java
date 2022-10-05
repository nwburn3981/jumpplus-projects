package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.cognixia.jump.exception.RecordNotFoundException;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.User;

public class UserDAO {

	private Connection conn = ConnectionManager.getConnection();

	public User getUserByName(String name) {

		User user = new User();

		PreparedStatement prep = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM users WHERE name = ?";

		try {
			prep = conn.prepareStatement(sql);

			prep.setString(1, name);
			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
					throw new RecordNotFoundException("No users found");
				}

				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setCity(rs.getString(5));
				user.setStreet(rs.getString(6));
				user.setPhoneNumber(rs.getString(7));
			}

		} catch (RecordNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			prep.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

	public User getUserById(int id) {

		User user = new User();

		PreparedStatement prep = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM users WHERE user_id = ?";

		try {
			prep = conn.prepareStatement(sql);

			prep.setInt(1, id);
			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
					throw new RecordNotFoundException("No users found");
				}

				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setCity(rs.getString(5));
				user.setStreet(rs.getString(6));
				user.setPhoneNumber(rs.getString(7));
			}

		} catch (RecordNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			prep.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

	public int verifyUser(String username, String password) {

		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = "SELECT user_id FROM users WHERE username = ? && password = ?";

		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, username);
			prep.setString(2, password);

			rs = prep.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			prep.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public boolean createUser(User user) {

		PreparedStatement prep = null;
		int numInserts = 0;
		String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?)";

		try {
			prep = conn.prepareStatement(sql);

			prep.setNull(1, Types.INTEGER);
			prep.setString(2, user.getName());
			prep.setString(3, user.getUsername());
			prep.setString(4, user.getPassword());
			prep.setString(5, user.getCity());
			prep.setString(6, user.getStreet());
			prep.setString(7, user.getPhoneNumber());

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("User " + user.getUsername() + " created.");
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

	public boolean updateUser(User user) {

		PreparedStatement prep = null;
		int numInserts = 0;
		String sql = "UPDATE users SET name = ? username = ?, password = ?, city = ?, street_address = ?, phone_number = ? WHERE user_id = ?)";

		try {
			prep = conn.prepareStatement(sql);

			prep.setString(1, user.getName());
			prep.setString(2, user.getUsername());
			prep.setString(3, user.getPassword());
			prep.setString(4, user.getCity());
			prep.setString(5, user.getStreet());
			prep.setString(6, user.getPhoneNumber());
			prep.setInt(7, (int) user.getId());

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("User " + user.getUsername() + " updated.");
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

}
