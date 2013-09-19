package contollers;

import java.sql.Timestamp;
import java.util.Date;

import beans.AuctionListBean;
import beans.BidBean;
import beans.BidListBean;
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
	public void addBid(String username) {
		BidListBean blb = getBids();
		AuctionListBean alb = JDBCConnector.getAuction(Integer.parseInt(paramManager.getIndividualParam("id")), null, null);
		float bid = Float.parseFloat(paramManager.getIndividualParam("bid"));
		if (alb.getAuctions().isEmpty()) return ;
		
		if (bid >= alb.getAuctions().get(0).getReservePrice()) {
			//TODO automatic winner of auction
		} else {
			for (BidBean b : blb.getBids())
				if (bid < b.getPrice() + alb.getAuctions().get(0).getBiddingIncrements())
					return ;
		
			JDBCConnector.addBidding(username, Integer.parseInt(paramManager.getIndividualParam("id")), Float.parseFloat(paramManager.getIndividualParam("bid")), new Timestamp(new Date().getTime()));
		}
	}
	
	//TODO fix integer parsing
	public BidListBean getBids() {
		return JDBCConnector.getBiddings(Integer.parseInt(paramManager.getIndividualParam("id")));
	}

}
