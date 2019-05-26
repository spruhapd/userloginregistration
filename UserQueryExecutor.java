package com.green.finch.dbexecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.green.finch.model.Login;
import com.green.finch.model.User;

@Component
public class UserQueryExecutor {

	public User getRegisterUser(String userName) {
		Statement stmt = null;
		Connection c = null;
		User user = new User();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:core.db");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER_DETAIL where user_name = '" + userName + "'");
			while (rs.next()) {
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("user_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(stmt, c);
		}
		return user;
	}

	/**
	 * Loading the cache for URLS.
	 */
	public User validateLoggedInUser(Login login) {
		Statement stmt = null;
		Connection c = null;
		User user = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:core.db");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER_DETAIL where user_name = '" + login.getUserName() + "' and password ='"+login.getPassword()+"'");
			while (rs.next()) {
				user = new User();
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("user_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(stmt, c);
		}
		return user;
	}

	public int executeQuery(String query) {
		Statement stmt = null;
		Connection c = null;
		int rows = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:core.db");
			stmt = c.createStatement();
			rows = stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(stmt, c);
		}

		return rows;
	}

	public int createUser(User user) {
		return executeQuery("insert into USER_DETAIL (user_name, password, first_name, last_name ) " + "values (\""
				+ user.getUserName() + "\",\"" + user.getPassword() + "\",\"" + user.getFirstName() + "\",\""
				+ user.getLastName() + "\")");
	}

	private static void closeConnection(Statement stmt, Connection c) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}

	/**
	 * Creates URL table in database on startup.
	 */
	public void createTables() {
		String createTable = "CREATE TABLE IF NOT EXISTS USER_DETAIL (user_name TEXT PRIMARY KEY NOT NULL,"
				+ "password TEXT NOT NULL, first_name TEXT NOT NULL, last_name TEXT NOT NULL)";

		executeQuery(createTable);
	}

}
