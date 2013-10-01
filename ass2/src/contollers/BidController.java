package contollers;

import java.sql.Timestamp;
import java.util.Date;

import beans.AuctionListBean;
import beans.BidBean;
import beans.BidListBean;
import ass2.Emailer;
import ass2.FormManager;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class BidController extends MasterFormBasedController {

	private static final String LOST_BID = "You have lost a bid!";
	
	public BidController(ParameterManager params) {
		super(params);
	}

	@Override
	protected void createForm() {
		if (paramManager.hasParameter("bid")) formManager.addForm("bid", paramManager.getIndividualParam("bid"), FormManager.RESTRICT_FLOAT_ONLY);
	}
	
	//TODO this makes many calls to DB, fix (also fix parsing numbers)
	public boolean addBid(String username, String email, String url) {
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
			String msg = "You have completed an auction. Your partner in this auction can be contacted on the email: " + tmp + ". You can view the auction here: " + url + "?action=auction&id=" + alb.getAuctions().get(0).getId();
			Emailer e = new Emailer(auctionEmail, title, msg);
			e.email();
			tmp = auctionEmail;
			e = new Emailer(bidEmail, title, msg);
			e.email();
			
			message = "you have won the bid<br>";
			return false;
		} else {
			if (bid < alb.getAuctions().get(0).getStartPrice()) {
				message = "bid must be greater than start price<br>";
				return true;
			}
			
			if (!blb.getBids().isEmpty() && bid < blb.getBids().get(0).getPrice() + alb.getAuctions().get(0).getBiddingIncrements()) {
				message = "bid must be greater than or equal to the highest bid plus the increment<br>";
				return true;
			}
		
			JDBCConnector.addBidding(username, Integer.parseInt(paramManager.getIndividualParam("id")), Float.parseFloat(paramManager.getIndividualParam("bid")), new Timestamp(new Date().getTime()));
			message = "bid placed successfully<br>";
			
			if (!blb.getBids().isEmpty())
				JDBCConnector.addAlert(blb.getBids().get(0).getAuthor(), Integer.parseInt(paramManager.getIndividualParam("id")), LOST_BID);
			
			//notify email
			String title = "You have place a winning bid!.";
			String msg = "You have placed a winning bid. You can view the auction here: " + url + "?action=auction&id=" + alb.getAuctions().get(0).getId();
			Emailer e = new Emailer(email, title, msg);
			e.email();
			
		}
		return true;
	}
	
	//TODO fix integer parsing
	public BidListBean getBids() {
		return JDBCConnector.getBiddings(Integer.parseInt(paramManager.getIndividualParam("id")), false);
	}

}
