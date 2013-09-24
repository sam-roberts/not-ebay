package contollers;

import beans.AlertListBean;
import beans.AuctionListBean;
import beans.BidListBean;
import beans.WinningAuctionListBean;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class AlertController extends MasterFormBasedController {
	
	public AlertController(ParameterManager params) {
		super(params);
	}

	protected void createForm() {
	}

	//TODO set messages, also Integer.parseInt blows up if fails
	public AlertListBean getAlert(String username) {
		return JDBCConnector.getAlerts(username);
	}
	
	public void removeAlert(String username) {
		JDBCConnector.removeAlert(Integer.parseInt(paramManager.getIndividualParam("id")), username);
	}
	
}
