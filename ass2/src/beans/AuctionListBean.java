package beans;

import java.util.LinkedList;

public class AuctionListBean {

	LinkedList<AuctionBean> auctions;
	
	public AuctionListBean() {
		auctions = new LinkedList<AuctionBean>();
	}
	
	public void addAuction(AuctionBean a) {
		auctions.add(a);
	}
	
	public int getNumAuctions() {
		return auctions.size();
	}
	
	//TODO stylize
	public String display() {
		String strConcat = "";
		for (AuctionBean ab : auctions)
			strConcat += ab.display();
		return strConcat;
	}
}
