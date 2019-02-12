package com.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.data.UserData;
import com.data.LoginPage;

public class DataBaseConnection {
	UserData ud = new UserData();
	LoginPage lp = new LoginPage();

	private String url = "jdbc:mysql://remotemysql.com:3306/fZQSSL8KHn?useUnicode=true&characterEncoding=utf-8";
	private String user = "fZQSSL8KHn";
	private String password = "RkLv1YP2lK";

	public Connection connect() {

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
		}
		return conn;
	}

	public String[] selectData() {

		String[] data = new String[4];

		UserData ud = new UserData();

		String sql = "SELECT * FROM users";
		Connection conn = null;
		Statement state = null;
		ResultSet result = null;

		try {
			conn = this.connect();
			state = conn.createStatement();
			result = state.executeQuery(sql);

			while (result.next()) {
				if (result.getString("macAdress").equals(ud.getMacAddressByNetworkInterface())) {
					data[0] = result.getString("userName");
					data[1] = result.getString("macAdress");
					data[2] = String.valueOf(result.getInt("id"));
					data[3] = String.valueOf(result.getInt("legalUser"));
				}
			}
		} catch (Exception e) {
		
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				
			}
		}
		return data;
	}

	public void insertData(String userName) {
		String sql = "INSERT INTO users(macAdress,userName) VALUES(?,?)";
		Connection conn = null;
		PreparedStatement pState = null;
		try {
			conn = connect();
			pState = conn.prepareStatement(sql);

			pState.setString(1, ud.getMacAddressByNetworkInterface());
			pState.setString(2, userName);
			pState.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	// public static void main(String[] args) {
	// DataBaseConnection db = new DataBaseConnection();
	// db.connect();
	//
	// }

}