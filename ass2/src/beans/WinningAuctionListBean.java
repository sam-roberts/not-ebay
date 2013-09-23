package beans;

import java.util.LinkedList;

public class WinningAuctionListBean {

	private LinkedList<WinningAuctionBean> auctions;
	
	public WinningAuctionListBean() {
		auctions = new LinkedList<WinningAuctionBean>();
	}
	
	public void addAuction(WinningAuctionBean a) {
		auctions.add(a);
	}
	
	public LinkedList<WinningAuctionBean> getAuctions() {
		return auctions;
	}
	
	public boolean isEmpty() {
		return auctions.isEmpty();
	}
	
	public int getNumAuctions() {
		return auctions.size();
	}
	
	//TODO stylize
	public String display() {
		String strConcat = "";
		if (auctions.isEmpty()) {
			strConcat = "No winning auctions found.";
		} else {
			for (WinningAuctionBean ab : auctions)
				strConcat += ab.display();
		}
		return strConcat;
	}
}
