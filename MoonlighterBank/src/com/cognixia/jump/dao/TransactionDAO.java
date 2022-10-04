package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.exception.RecordNotFoundException;
import com.cognixia.jump.model.Transaction;

public class TransactionDAO {

	private Connection conn = ConnectionManager.getConnection();

	public boolean createTransaction(Transaction trans) {

		PreparedStatement prep = null;
		int numInserts = 0;
		String sql = "INSERT INTO transactions VALUES(?, ?, ?, ?, ?)";

		try {
			prep = conn.prepareStatement(sql);

			prep.setNull(1, Types.INTEGER);
			prep.setString(2, trans.getDescription());
			prep.setInt(3, trans.getInitialAccountId());
			prep.setInt(4, trans.getEndAccountId());
			prep.setString(5, trans.getTimestamp().toString());

			numInserts = prep.executeUpdate();

			if (numInserts > 0) {
				System.out.println("Transaction logged.");
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

	public ArrayList<Transaction> findLast5Transactions(int id) {

		ArrayList<Transaction> trans = new ArrayList<Transaction>();

		PreparedStatement prep = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM transactions \r\n" + "WHERE initial_account_id OR end_account_id \r\n" + "IN \r\n"
				+ "(SELECT account_id FROM accounts\r\n" + "WHERE user_id = ?);";

		try {

			prep = conn.prepareStatement(sql);

			prep.setInt(1, id);

			rs = prep.executeQuery();

			while (rs.next()) {
				if (rs.getRow() == 0) {
					throw new RecordNotFoundException("No transactions found");
				}
			}

			Transaction tran = new Transaction();
			tran.setId(rs.getInt(1));
			tran.setDescription(rs.getString(2));
			tran.setInitialAccountId(rs.getInt(3));
			tran.setEndAccountId(rs.getInt(4));
			tran.setTimestamp(LocalDateTime.parse(rs.getString(5)));

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

		return trans;

	}

}
