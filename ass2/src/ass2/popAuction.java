package ass2;

import contollers.GetAuctionController;

//TODO add a flag and store in controller when someone wins bid

public class popAuction implements Runnable {
	
	private int id;
	private boolean stop;
	private String url;
	
	public popAuction(int id, String url) {
		this.id = id;
		stop = false;
		this.url = url;
	}
	
	public void run() {
		if (stop) return;
		
		System.out.println("popping auction: " + id);
		//GetAuctionController gac = new GetAuctionController(null);
		GetAuctionController.popAuction(id, false, url);
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
