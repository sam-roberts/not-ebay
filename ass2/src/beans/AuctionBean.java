package beans;

import java.sql.Timestamp;

public class AuctionBean {

	private int id;
	private String title;
	private String author;
	private String category;
	private String picture;
	private String description;
	private String postageDetails;
	private float reservePrice;
	private float startPrice;
	private float biddingIncrements;
	private Timestamp endOfAuction;
	private boolean halt;
	
	public AuctionBean(int id, String title, String author, String category,
			String picture, String description, String postageDetails,
			float reservePrice, float startPrice, float biddingIncrements,
			Timestamp endOfAuction, boolean halt) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.picture = picture;
		this.description = description;
		this.postageDetails = postageDetails;
		this.reservePrice = reservePrice;
		this.startPrice = startPrice;
		this.biddingIncrements = biddingIncrements;
		this.endOfAuction = endOfAuction;
		this.halt = halt;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public String getPicture() {
		return picture;
	}

	public String getDescription() {
		return description;
	}

	public String getPostageDetails() {
		return postageDetails;
	}

	public float getReservePrice() {
		return reservePrice;
	}

	public float getStartPrice() {
		return startPrice;
	}

	public float getBiddingIncrements() {
		return biddingIncrements;
	}

	public Timestamp getEndOfAuction() {
		return endOfAuction;
	}

	public boolean isHalt() {
		return halt;
	}
	
	//TODO: STYLIZE THIS
	public String display() {
		return "<a href='controller?action=auction&id=" + id + "'>" + title + "</a><img src='" + picture + "'>";
	}

}
