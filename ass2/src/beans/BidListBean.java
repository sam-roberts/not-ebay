package beans;

import java.util.LinkedList;

public class BidListBean {

	private LinkedList<BidBean> bids;

	public BidListBean() {
		bids = new LinkedList<BidBean>();
	}
	
	public void addBid(BidBean b) {
		bids.add(b);
	}

	//TODO stylize
	public String display() {
		String strConcat = "";
		for (BidBean bb : bids)
			strConcat += bb.display();
		return strConcat;
	}
	
}