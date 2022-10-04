package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.exception.RecordNotFoundException;
import com.cognixia.jump.model.Account;

public class AccountDAO {

	private Connection conn = ConnectionManager.getConnection();

	public ArrayList<Account> findAllAccountsByUser(int id) {

		ArrayList<Account> accounts = new ArrayList<Account>();

		PreparedStatement prep = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM accounts WHERE user_id = ?";

		try {

			prep = conn.prepareStatement(sql);

			prep.setInt(1, id);

			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
					throw new RecordNotFoundException("No accounts found");
				}

				Account account = new Account();
				account.setId(rs.getInt(1));
				account.setBalance(rs.getDouble(2));
				account.setCreated(rs.getTimestamp(3));
				account.setAccountType(rs.getString(4));
				account.setUserId(rs.getInt(5));

				accounts.add(account);
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

		return accounts;

	}

	public Account findAccountByTimestamp(Timestamp stamp) {

		Account account = new Account();

		PreparedStatement prep = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM accounts WHERE created = ?";

		try {

			prep = conn.prepareStatement(sql);

			prep.setTimestamp(1, stamp);

			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
					throw new RecordNotFoundException("No accounts found");
				}

				account.setId(rs.getInt(1));
				account.setBalance(rs.getDouble(2));
				account.setCreated(rs.getTimestamp(3));
				account.setAccountType(rs.getString(4));
				account.setUserId(rs.getInt(5));

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

		return account;
	}
	
	public boolean createAccount(Account account) {
		
		PreparedStatement prep = null;
		int numInserts = 0;
		String sql = "INSERT INTO accounts VALUES(?, ?, ?, ?, ?)";

		try {
			prep = conn.prepareStatement(sql);

			prep.setNull(1, Types.INTEGER);
			prep.setDouble(2, account.getBalance());
			prep.setTimestamp(3, account.getCreated());
			prep.setString(4, account.getAccountType());
			prep.setInt(5, account.getUserId());

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("Account " + account.getAccountType() + account.getId() + " created.");
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
	
	public boolean updateAccountBalance(int id, double balance) {
		
		PreparedStatement prep = null;
		int numInserts = 0;
		String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?)";

		try {
			prep = conn.prepareStatement(sql);

			prep.setDouble(1, balance);
			prep.setInt(2, id);
	
			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("Balance: " + balance);
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
