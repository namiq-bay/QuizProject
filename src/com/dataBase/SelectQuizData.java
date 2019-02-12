package com.dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SelectQuizData {

	public static QuizData data[] = new QuizData[1000];

	private static String sql = "SELECT * FROM quiz";

	public void selectData() {

		DataBaseConnection conn = new DataBaseConnection();
		Connection connect = null;
		Statement state = null;
		ResultSet result = null;

		try {
			connect = conn.connect();
			state = connect.createStatement();
			result = state.executeQuery(sql);

			int count = 0;
			while (result.next()) {

				String id = String.valueOf(result.getInt(1));
				String userID = String.valueOf(result.getInt(2));
				String userName = result.getString(3);
				String quizName = result.getString(4);
				String trueCount = String.valueOf(result.getInt(5));
				String falseCount = String.valueOf(result.getInt(6));

				data[count++] = new QuizData(id, userID, userName, quizName, trueCount, falseCount);
			}

		} catch (Exception e) {

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
