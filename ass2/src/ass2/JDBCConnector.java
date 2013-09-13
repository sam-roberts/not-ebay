package ass2;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {

	private final String postgreConn = "jdbc:postgresql://127.0.0.1:5432/ass2";
	private final String user = "test";
	private final String pass = "test";
	
	private Connection c;
	
	public void addUser(String username, String password, String email, String nickname, String firstName, String lastName, int yearOfBirth, String postalAddress, int CCNumber) {
		try {
			connect();
			PreparedStatement ps = c.prepareStatement("INSERT INTO username VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, nickname);
			ps.setString(5, firstName);
			ps.setString(6, lastName);
			ps.setInt(7, yearOfBirth);
			ps.setString(8, postalAddress);
			ps.setInt(9, CCNumber);
			ps.execute();
		} catch (Exception e) {
			System.out.println("Could not add user.");
		}
		close();
	}
	
	private void connect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No postgresql driver.");
		}
		
		try {
			c = DriverManager.getConnection(postgreConn, user, pass);
			System.out.println("Successfully connected.");
		} catch (SQLException e) {
			System.out.println("Error JDBC can't connect.");
		}
	}
	
	private void close() {
		try {
			if (!c.isClosed())
				c.close();
		} catch (SQLException e) {
			System.out.println("Cannot close connection.");
		}
	}
}