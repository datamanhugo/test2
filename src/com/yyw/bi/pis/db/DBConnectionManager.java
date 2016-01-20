package com.yyw.bi.pis.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {

	private String driver;
	private String url;
	private String user;
	private String password;

	private static DBConnectionManager instance;

	public static DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		return instance;
	}

	public DBConnectionManager(String driver, String url, String user,
			String password) {
		try {
			this.driver = driver;
			this.url = url;
			this.user = user;
			this.password = password;
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DBConnectionManager() {
		try {
			this.driver = "com.mysql.jdbc.Driver";
			this.url = "jdbc:mysql://localhost/crawler";
			this.user = "root";
			this.password = "";
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		return conn;
	}

	public void releaseDBConnection(java.sql.Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void releaseDBStatement(java.sql.Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		// String url =
		// "jdbc:mysql://localhost/crawler useUnicode=true&characterEncoding=utf-8";
		String url = "jdbc:mysql://localhost/crawler";

		String user = "root";
		String password = "";

		try {
			DBConnectionManager connectionManager = new DBConnectionManager(
					driver, url, user, password);
			Connection connection = connectionManager.getConnection();
			if (!connection.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			Statement statement = connection.createStatement();
			statement.execute("set Names utf8");

			String sql = "select title from crawler.medicine_info limit 0,50 ";
			// String sql =
			// "select title from crawler.medicine_info limit 0,50 ";

			ResultSet rs = statement.executeQuery(sql);
			System.out.println("SQL:" + sql);

			// ���idֵ��contentֵ
			while (rs.next()) {
				System.out.println(rs.getString("title"));
			}
			rs.close();
			connectionManager.releaseDBConnection(connection);
			System.out.println("Succeeded connecting to the Database!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
