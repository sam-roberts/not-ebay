package beans;

public class WinningAuctionBean {

	private int id;
	private int auctionID;
	private String auctionTitle;
	private float auctionReservePrice;
	private int bidID;
	private float bidPrice;

	public WinningAuctionBean(int id, int auctionID, String auctionTitle,
			float auctionReservePrice, int bidID, float bidPrice) {
		super();
		this.id = id;
		this.auctionID = auctionID;
		this.auctionTitle = auctionTitle;
		this.auctionReservePrice = auctionReservePrice;
		this.bidID = bidID;
		this.bidPrice = bidPrice;
	}

	public int getId() {
		return id;
	}

	public int getAuctionID() {
		return auctionID;
	}

	public String getAuctionTitle() {
		return auctionTitle;
	}

	public float getAuctionReservePrice() {
		return auctionReservePrice;
	}

	public int getBidID() {
		return bidID;
	}

	public float getBidPrice() {
		return bidPrice;
	}

	//TODO: STYLIZE THIS
	public String display() {
		String s = "<a href='controller?action=auction&id=" + auctionID + "'>" + auctionTitle + "</a><br>Reserve Price: " + auctionReservePrice + "<br>Your bid: " + bidPrice + "<br>";
		if (bidPrice < auctionReservePrice) s += "You must pay the reserve price to win this auction.<br>";
		s += "<form action='controller?action=win' method='POST'><input type='hidden' name='id' value='" + id + "'><input type='submit' value='Win'></form>";
		return s;
	}

}
