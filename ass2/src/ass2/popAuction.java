package ass2;

import contollers.GetAuctionController;

//TODO add a flag and store in controller when someone wins bid

public class popAuction implements Runnable {
	
	int id;
	boolean stop;
	
	public popAuction(int id) {
		this.id = id;
		stop = false;
	}
	
	public void run() {
		if (stop) return;
		
		System.out.println("popping auction: " + id);
		//GetAuctionController gac = new GetAuctionController(null);
		GetAuctionController.popAuction(id, false);
	}
	
	public void stop() {
		stop = true;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof popAuction) {
			popAuction pa = (popAuction) o;
			return id == pa.id;
		}
		return false;
	}

}
