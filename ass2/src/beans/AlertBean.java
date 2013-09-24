package beans;

import java.sql.Timestamp;

public class AlertBean {

	private int id;
	private String username;
	private int auctionID;
	private String message;
	
	public AlertBean(int id, String username, int auctionID, String message) {
		super();
		this.id = id;
		this.username = username;
		this.auctionID = auctionID;
		this.message = message;
	}
	
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public int getAuctionID() {
		return auctionID;
	}

	public String getMessage() {
		return message;
	}

	//TODO: STYLIZE THIS
	public String display() {
		String s = "message: " + message + "<br>";
		if (auctionID != 0) s += "auction: " + auctionID + "<br>";
		s += "<form action='controller?action=removeAlert' method='POST'><input type='hidden' name='id' value='" + id + "'><input type='submit' value='Remove'></form>";
		return s;
	}
	
}
