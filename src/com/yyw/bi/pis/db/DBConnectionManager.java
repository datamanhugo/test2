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
			// 加载驱动程序
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
			// 加载驱动程序
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 建立新的连接返回连接句柄
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
		// URL指向要访问的数据库名******
		// String url =
		// "jdbc:mysql://localhost/crawler useUnicode=true&characterEncoding=utf-8";
		String url = "jdbc:mysql://localhost/crawler";

		// MySQL配置时的用户名
		String user = "root";
		// Java连接MySQL配置时的密码******
		String password = "";

		try {
			DBConnectionManager connectionManager = new DBConnectionManager(
					driver, url, user, password);
			Connection connection = connectionManager.getConnection();
			if (!connection.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			// statement用来执行SQL语句
			Statement statement = connection.createStatement();
			statement.execute("set Names utf8");

			// 要执行的SQL语句id和content是表review中的项。
			String sql = "select title from crawler.medicine_info where site='康爱多官网' and date='2016-01-13' AND type='中西药' limit 0,50 ";
			// String sql =
			// "select title from crawler.medicine_info limit 0,50 ";

			ResultSet rs = statement.executeQuery(sql);
			System.out.println("SQL:" + sql);

			// 输出id值和content值
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
