package ass2;

import contollers.GetAuctionController;

public class popAuction implements Runnable {
	int id;
	
	public popAuction(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("popping auction: " + id);
		GetAuctionController.popAuction(id);
	}

}
