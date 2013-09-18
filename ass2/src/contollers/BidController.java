package contollers;

import java.sql.Timestamp;
import java.util.Date;

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
	
	public void addBid(String username) {
		JDBCConnector.addBidding(username, Integer.parseInt(paramManager.getIndividualParam("id")), Float.parseFloat(paramManager.getIndividualParam("bid")), new Timestamp(new Date().getTime()));
	}
	
	public BidListBean getBids() {
		return JDBCConnector.getBiddings(Integer.parseInt(paramManager.getIndividualParam("id")));
	}

}
