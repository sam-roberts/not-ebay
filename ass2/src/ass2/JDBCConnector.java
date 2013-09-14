package ass2;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import beans.UserBean;

public class JDBCConnector {

	//Possibly store these in a file rather than in code
	private final String postgreConn = "jdbc:postgresql://127.0.0.1:5432/ass2";
	private final String user = "test";
	private final String pass = "test";
	
	private Connection c;
	
	private void connect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No postgresql driver.");
		}
		
		try {
			c = DriverManager.getConnection(postgreConn, user, pass);
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
	
	public void addUser(String username, String password, String email, String nickname, String firstName, String lastName, int yearOfBirth, String postalAddress, int CCNumber, boolean banned) {
		try {
			connect();
			PreparedStatement ps = c.prepareStatement("INSERT INTO username VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, nickname);
			ps.setString(5, firstName);
			ps.setString(6, lastName);
			ps.setInt(7, yearOfBirth);
			ps.setString(8, postalAddress);
			ps.setInt(9, CCNumber);
			ps.setBoolean(10, banned);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Could not add user.");
		}
		close();
	}
	
	public boolean userExists(String username) {
		try {
			connect();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM username WHERE username=?");
			ps.setString(1, username);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			return (rs.next()) ? true : false;
		} catch (SQLException e) {
			System.out.println("Could not check user.");
		}
		close();
		return false;
	}
	
	public UserBean getUserBean(String username) {
		try {
			connect();
			PreparedStatement ps = c.prepareStatement("SELECT username, email_address, nickname, first_name, last_name, year_of_birth, postal_address FROM username WHERE username=?");
			ps.setString(1, username);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new UserBean(
						rs.getString("username"),
						rs.getString("email_address"),
						rs.getString("nickname"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getInt("year_of_birth"),
						rs.getString("postal_address")
				);
			}
		} catch (SQLException e) {
			System.out.println("Could not get userbean.");
		}
		close();
		return null;
	}
	
	public void addAuction(String title, String author, String category, String picture, String description, String postageDetails, float reservePrice, float startPrice, float biddingIncrements, Timestamp date, boolean halt) {
		try {
			connect();
			PreparedStatement ps = c.prepareStatement("INSERT INTO Auction VALUES (DEFAULT, ?, (SELECT username FROM Username WHERE username=?), ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, title);
			ps.setString(2, author);
			ps.setString(3, category);
			ps.setString(4, picture);
			ps.setString(5, description);
			ps.setString(6, postageDetails);
			ps.setFloat(7, reservePrice);
			ps.setFloat(8, startPrice);
			ps.setFloat(9, biddingIncrements);
			ps.setTimestamp(10, date);
			ps.setBoolean(11, halt);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Could not add auction.");
		}
		close();
	}
	
	public void addBidding(String author, int auctionID, float price, Timestamp bidDate) {
		try {
			connect();
			PreparedStatement ps = c.prepareStatement("INSERT INTO Bidding VALUES (DEFAULT, (SELECT username FROM Username WHERE username=?), (SELECT id FROM Auction WHERE id=?), ?, ?)");
			ps.setString(1, author);
			ps.setInt(2, auctionID);
			ps.setFloat(3, price);
			ps.setTimestamp(4, bidDate);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Could not add bidding.");
		}
		close();
	}
	
}