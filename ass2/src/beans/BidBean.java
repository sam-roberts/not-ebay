package beans;

public class BidBean {

	private String author;
	private float price;
	
	public BidBean(String author, float price) {
		super();
		this.author = author;
		this.price = price;
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
