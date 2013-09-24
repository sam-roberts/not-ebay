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
	
	public WinningAuctionListBean getWinningAuctions(String author) {
		return JDBCConnector.getWinningAuctions(author);
	}
	
	//TODO fix static
	public static void popAuction(int id) {
		BidListBean biddings = JDBCConnector.getBiddings(id, true);
		//TODO make transaction i suppose?
		JDBCConnector.finishAuction(id);
		if (!biddings.getBids().isEmpty()) {
			JDBCConnector.addWinningAuction(id, biddings.getBids().get(0).getID());
		} else {
			System.out.println("ERRR");
			//TODO give back a message
		}
	}
	
	//TODO This shoul dbe a transaction AND check if the input is safe
	public void winAuction() {
		//TODO get the emails and email them i suppose
		
		
		JDBCConnector.deleteAuction(Integer.parseInt(paramManager.getIndividualParam("id")));
	}
	
}
