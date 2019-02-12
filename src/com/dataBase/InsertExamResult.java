package com.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class InsertExamResult {

	public static void insert(String quizName, int trueCount, int falseCount) {
		DataBaseConnection db = new DataBaseConnection();
		String[] userData = db.selectData();

		Connection connect = null;
		PreparedStatement pState = null;
		String sql = "INSERT INTO quiz(userID, userName, quizName, trueCount, falseCount) VALUES(?,?,?,?,?)";

		try {
			connect = db.connect();
			pState = connect.prepareStatement(sql);

			pState.setInt(1, Integer.valueOf(userData[2]));
			pState.setString(2, userData[0]);
			pState.setString(3, quizName);
			pState.setInt(4, trueCount);
			pState.setInt(5, falseCount);
			pState.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			try {
				if (!connect.isClosed())
					connect.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
}
