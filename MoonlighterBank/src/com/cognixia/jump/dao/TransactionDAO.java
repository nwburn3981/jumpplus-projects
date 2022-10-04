package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.cognixia.jump.connection.ConnectionManager;
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
			prep.setTimestamp(5, trans.getTimestamp());

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

}
