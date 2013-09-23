package beans;

public class BidBean {

	private int id;
	private String author;
	private float price;
	
	public BidBean(int id, String author, float price) {
		super();
		this.id = id;
		this.author = author;
		this.price = price;
	}

	public int getID() {
		return id;
	}
	
	public String getAuthor() {
		return author;
	}

	public float getPrice() {
		return price;
	}

	public String display() {
		return "<b>" + author + "</b> - price: " + price + "<br>";
	}
	
}
