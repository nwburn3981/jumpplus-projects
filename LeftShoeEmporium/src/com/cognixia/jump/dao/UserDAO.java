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
import com.cognixia.jump.model.User;

public class UserDAO {

	private Connection conn = ConnectionManager.getConnection();

	public List<User> findAll() {

		List<User> users = new ArrayList<>();
		PreparedStatement prep = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM users";

		try {
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
					throw new RecordNotFoundException("No users found");
				}

				User user = new User();
				user.setUser_id(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirst_name(rs.getString(4));
				user.setLast_name(rs.getString(5));

				users.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		return users;
	}

	public boolean create(User user) {

		PreparedStatement prep = null;
		int numInserts = 0;
		String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";

		try {
			prep = conn.prepareStatement(sql);

			prep.setNull(1, Types.INTEGER);
			prep.setString(2, user.getUsername());
			prep.setString(3, user.getPassword());
			prep.setString(4, user.getFirst_name());
			prep.setString(5, user.getLast_name());

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("User " + user.getUsername() + " created.");
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

	public boolean update(User user) {

		PreparedStatement prep = null;
		int numInserts = 0;
		String sql = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ?, WHERE user_id = ?)";

		try {
			prep = conn.prepareStatement(sql);

			prep.setString(1, user.getUsername());
			prep.setString(2, user.getPassword());
			prep.setString(3, user.getFirst_name());
			prep.setString(4, user.getLast_name());
			prep.setInt(5, (int) user.getUser_id());

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("User " + user.getUsername() + " updated.");
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

	public int verifyUser(User user) {

		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = "SELECT user_id FROM users WHERE username = ? && password = ?";

		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, user.getUsername());
			prep.setString(2, user.getPassword());

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

}
