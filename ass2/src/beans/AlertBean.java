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
		return (auctionID == 0) ? "message: " + message + "<br>" : "auction: " + auctionID + "<br>message: " + message + "<br>";
	}
	
}
