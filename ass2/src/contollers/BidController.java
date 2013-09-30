package contollers;

import java.sql.Timestamp;
import java.util.Date;

import beans.AuctionListBean;
import beans.BidBean;
import beans.BidListBean;
import ass2.Emailer;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class BidController extends MasterFormBasedController {

	public BidController(ParameterManager params) {
		super(params);
	}

	@Override
	protected void createForm() {
		if (paramManager.hasParameter("bid")) formManager.addForm("bid", paramManager.getIndividualParam("bid"));
	}
	
	//TODO this makes many calls to DB, fix (also fix parsing numbers)
	public boolean addBid(String username) {
		BidListBean blb = getBids();
		AuctionListBean alb = JDBCConnector.getAuction(Integer.parseInt(paramManager.getIndividualParam("id")), null, null, false);
		float bid = Float.parseFloat(paramManager.getIndividualParam("bid"));
		if (alb.getAuctions().isEmpty()) return true;
		
		if (bid >= alb.getAuctions().get(0).getReservePrice()) {
			//TODO automatic winner of auction
			String auctionEmail = JDBCConnector.getUserBean(alb.getAuctions().get(0).getAuthor(), false).getEmail();
			String bidEmail = JDBCConnector.getUserBean(username, false).getEmail();
			String tmp = bidEmail;
			String title = "You have won.";
			String msg = "You have completed an auction. Your partner in this auction can be contacted on the email: " + tmp;
			Emailer e = new Emailer(auctionEmail, title, msg);
			e.email();
			tmp = auctionEmail;
			e = new Emailer(bidEmail, title, msg);
			e.email();
			return false;
		} else {
			for (BidBean b : blb.getBids())
				if (bid < b.getPrice() + alb.getAuctions().get(0).getBiddingIncrements())
					return true;
		
			JDBCConnector.addBidding(username, Integer.parseInt(paramManager.getIndividualParam("id")), Float.parseFloat(paramManager.getIndividualParam("bid")), new Timestamp(new Date().getTime()));
		}
		return true;
	}
	
	//TODO fix integer parsing
	public BidListBean getBids() {
		return JDBCConnector.getBiddings(Integer.parseInt(paramManager.getIndividualParam("id")), false);
	}

}
