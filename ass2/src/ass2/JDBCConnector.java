package ass2;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import beans.AuctionBean;
import beans.UserBean;

public class JDBCConnector {

	//Possibly store these in a file rather than in code
	private static final String postgreConn = "jdbc:postgresql://127.0.0.1:5432/ass2";
	private static final String user = "test";
	private static final String pass = "test";
	
	private static Connection connect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No postgresql driver.");
		}
		
		try {
			return DriverManager.getConnection(postgreConn, user, pass);
		} catch (SQLException e) {
			System.out.println("Error JDBC can't connect.");
		}
		
		return null;
	}
	
	private static void close(Connection c) {
		try {
			if (!c.isClosed())
				c.close();
		} catch (SQLException e) {
			System.out.println("Cannot close connection.");
		}
	}
	
	public static void addUser(String username, String password, String email, String nickname, String firstName, String lastName, int yearOfBirth, String postalAddress, int CCNumber, boolean banned) {
		Connection c = null;
		try {
			c = connect();
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
			System.out.println("Could not add user.\n" + e.getMessage());
		}
		close(c);
	}
	
	public static boolean isUserExists(String username) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(username) AS total FROM username WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return (rs.getInt("total") > 0) ? true : false;
			}
		} catch (SQLException e) {
			System.out.println("Could not check user.");
		}
		close(c);
		return false;
	}
	
	public static boolean hasLogin(String username, String password) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(username) AS total FROM username WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return (rs.getInt("total") > 0) ? true : false;
			}
		} catch (SQLException e) {
			System.out.println("Could not check user.");
		}
		close(c);
		return false;
	}
	
	public static UserBean getUserBean(String username) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("SELECT username, email_address, nickname, first_name, last_name, year_of_birth, postal_address FROM username WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				close(c);
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
		close(c);
		return null;
	}
	
	public static void addAuction(String title, String author, String category, String picture, String description, String postageDetails, float reservePrice, float startPrice, float biddingIncrements, Timestamp date, boolean halt) {
		Connection c = null;
		try {
			c = connect();
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
		close(c);
	}
	
	public static AuctionBean getAuction(int id) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Auction WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				close(c);
				return new AuctionBean(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("category"),
						rs.getString("picture"),
						rs.getString("description"),
						rs.getString("postage_details"),
						rs.getFloat("reserve_price"),
						rs.getFloat("start_price"),
						rs.getFloat("bidding_increments"),
						rs.getTimestamp("end_of_auction"),
						rs.getBoolean("halt")
				);
			}
		} catch (SQLException e) {
			System.out.println("Could not add auction.");
		}
		close(c);
		return null;
	}
	
	public static void addBidding(String author, int auctionID, float price, Timestamp bidDate) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("INSERT INTO Bidding VALUES (DEFAULT, (SELECT username FROM Username WHERE username=?), (SELECT id FROM Auction WHERE id=?), ?, ?)");
			ps.setString(1, author);
			ps.setInt(2, auctionID);
			ps.setFloat(3, price);
			ps.setTimestamp(4, bidDate);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Could not add bidding.");
		}
		close(c);
	}
	
	public static void clearDB() {
		Connection c = null;
		try {
			c = connect();
			Statement s = c.createStatement();
			s.execute("DELETE FROM Bidding WHERE 1=1");
			s.execute("DELETE FROM Auction WHERE 1=1");
			s.execute("DELETE FROM Username WHERE 1=1");
		} catch (SQLException e) {
			System.out.println("Could not clear DB (does not revert changes).");
		}
		close(c);
	}
	
}