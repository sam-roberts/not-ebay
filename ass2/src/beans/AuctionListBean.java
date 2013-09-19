package beans;

import java.util.LinkedList;

public class AuctionListBean {

	private LinkedList<AuctionBean> auctions;
	
	public AuctionListBean() {
		auctions = new LinkedList<AuctionBean>();
	}
	
	public void addAuction(AuctionBean a) {
		auctions.add(a);
	}
	
	public LinkedList<AuctionBean> getAuctions() {
		return auctions;
	}
	
	public int getNumAuctions() {
		return auctions.size();
	}
	
	//TODO stylize
	public String display() {
		String strConcat = "";
		if (auctions.isEmpty()) {
			strConcat = "No auctions found.";
		} else {
			for (AuctionBean ab : auctions)
				strConcat += ab.display();
		}
		return strConcat;
	}
}
