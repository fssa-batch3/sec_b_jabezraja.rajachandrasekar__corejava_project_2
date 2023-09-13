package in.fssa.jauntyrialto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	// this method will be use when ever we connect with database.
	
	/**
	 * 
	 * @return
	 */
	public static Connection getConnection() {

		final String url;
		final String userName;
		final String passWord;

		// CLOUD DATABASE
//		url = System.getenv("DATABASE_HOSTNAME");
//		userName = System.getenv("DATABASE_USERNAME");
//		passWord = System.getenv("DATABASE_PASSWORD");

		// LOCAL DATABASE

		url = System.getenv("LOCAL_DATABASE_HOSTNAME");
		userName = System.getenv("LOCAL_DATABASE_USERNAME");
		passWord = System.getenv("LOCAL_DATABASE_PASSWORD");

		Connection connection = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, passWord);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to connect Database");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Driver Class Not Found");
		}

		return connection;
	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 */
	public static void close(Connection connection, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 * @param rs
	 */

	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
		try {
			// the order of close is important.
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
