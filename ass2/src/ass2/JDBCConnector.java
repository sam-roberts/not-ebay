package ass2;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import beans.AuctionBean;
import beans.AuctionListBean;
import beans.BidBean;
import beans.BidListBean;
import beans.UserBean;
import beans.WinningAuctionBean;
import beans.WinningAuctionListBean;

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
			PreparedStatement ps = c.prepareStatement("SELECT banned FROM username WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return !rs.getBoolean("banned");
			}
		} catch (SQLException e) {
			System.out.println("Could not check user login.");
		}
		close(c);
		return false;
	}
	
	public static void banUser(String username) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("UPDATE username SET banned=TRUE WHERE username=?");
			ps.setString(1, username);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Could notban user.");
		}
		close(c);
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
	
	public static int addAuction(String title, String author, String category, String picture, String description, String postageDetails, float reservePrice, float startPrice, float biddingIncrements, Timestamp date, boolean halt) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("INSERT INTO Auction VALUES (DEFAULT, ?, (SELECT username FROM Username WHERE username=?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
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
			ps.setBoolean(12, false);
			ps.executeUpdate();
			
			ResultSet gk = ps.getGeneratedKeys();
			gk.next();
			return (int) gk.getLong(1);
		} catch (SQLException e) {
			System.out.println("Could not add auction.");
		}
		close(c);
		return 0;
	}
	
	public static AuctionListBean getAuction(int id, String author, String title) {
		Connection c = null;
		
		if (author != null)author = author.toLowerCase();
		if (title != null) title = title.toLowerCase();
		
		String query = "SELECT * FROM Auction WHERE 1=1 ";
		if (id > 0) query += "AND id=? ";
		if (author != null) query += "AND LOWER(author) LIKE ? ";
		if (title != null) query += "AND LOWER(title) LIKE ? ";
		query += "AND finished=FALSE";

		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement(query);
			
			//TODO fix this so it looks better
			int incre = 1;
			if (id > 0) ps.setInt(incre++, id);
			if (author != null) ps.setString(incre++, "%" + author + "%");
			if (title != null) ps.setString(incre++, "%" + title + "%");

			ResultSet rs = ps.executeQuery();
			AuctionListBean alb = new AuctionListBean();
			while (rs.next()) {
				close(c);	
				alb.addAuction(new AuctionBean(
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
						rs.getBoolean("halt"))
				);
			}
			return alb;
		} catch (SQLException e) {
			System.out.println("Could not get auctions.");
		}
		close(c);
		return null;
	}
	
	public static void deleteAuction(int id) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("SELECT auction FROM winningauction WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			int auctionID = 0;
			while (rs.next()) {
				auctionID = rs.getInt("auction");
			}
			ps = c.prepareStatement("DELETE FROM winningauction WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
			ps = c.prepareStatement("DELETE FROM bidding WHERE auction=?");
			ps.setInt(1, auctionID);
			ps.execute();
			ps = c.prepareStatement("DELETE FROM auction WHERE id=?");
			ps.setInt(1, auctionID);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Could not clean auction.");
		}
		close(c);
	}
	
	public static void haltAuction(int id) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("UPDATE auction SET halt=TRUE WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Could notban user.");
		}
		close(c);
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
	
	public static void finishAuction(int id) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("UPDATE auction SET finished=TRUE WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Could not finish auction.");
		}
		close(c);
	}
	
	public static BidListBean getBiddings(int id, boolean topBid) {
		Connection c = null;
		String query = "SELECT * FROM Bidding WHERE auction=(SELECT id FROM Auction WHERE id=?) ORDER BY price DESC";
		if (topBid) query += " LIMIT 1";
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			BidListBean blb = new BidListBean();
			while (rs.next()) {	
				blb.addBid(new BidBean(
						rs.getInt("id"),
						rs.getString("author"),
						rs.getFloat("price"))
				);
			}
			close(c);
			return blb;
		} catch (SQLException e) {
			System.out.println("Could not get biddings.");
		}
		close(c);
		return null;
	}
	
	public static void addWinningAuction(int auctionID, int bidID) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("INSERT INTO WinningAuction VALUES (DEFAULT, (SELECT id FROM Auction WHERE id=?), (SELECT id FROM Bidding WHERE id=?))");
			ps.setInt(1, auctionID);
			ps.setInt(2, bidID);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Could not add winning auction.");
		}
		close(c);
	}
	
	public static WinningAuctionListBean getWinningAuctions(String user) {
		Connection c = null;
		try {
			c = connect();
			PreparedStatement ps = c.prepareStatement("select wa.id as wid, a.id as aid, a.title as atitle, a.reserve_price as areserve_price, b.id as bid, b.price as bprice from winningauction wa inner join auction AS a on wa.auction=a.id inner join bidding b on wa.bid=b.id and b.author=(SELECT username FROM username WHERE username=?)");;
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			WinningAuctionListBean walb = new WinningAuctionListBean();
			while (rs.next()) {	
				walb.addAuction(new WinningAuctionBean(
						rs.getInt("wid"),
						rs.getInt("aid"),
						rs.getString("atitle"),
						rs.getFloat("areserve_price"),
						rs.getInt("bid"),
						rs.getFloat("bprice"))
				);
			}
			close(c);
			return walb;
		} catch (SQLException e) {
			System.out.println("Could not get winning auctions.");
		}
		close(c);
		return null;
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