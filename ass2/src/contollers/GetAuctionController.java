package contollers;

import beans.AuctionListBean;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class GetAuctionController extends MasterFormBasedController {
	
	public GetAuctionController(ParameterManager params) {
		super(params);
	}

	protected void createForm() {
		if (paramManager.hasParameter("id")) formManager.addForm("id", paramManager.getIndividualParam("id"));
		if (paramManager.hasParameter("author")) formManager.addForm("id", paramManager.getIndividualParam("author"));
		if (paramManager.hasParameter("title")) formManager.addForm("id", paramManager.getIndividualParam("title"));
	}

	//TODO set messages, also Integer.parseInt blows up if fails
	public AuctionListBean getAuction() {
		int id = (paramManager.hasParameter("id")) ? Integer.parseInt(paramManager.getIndividualParam("id")) : 0;
		String author = (paramManager.hasParameter("author")) ? paramManager.getIndividualParam("author") : null;
		String title = (paramManager.hasParameter("title")) ? paramManager.getIndividualParam("title") : null;
		return JDBCConnector.getAuction(id, author, title);
	}
	
	public void finishAuction() {
		
	}
	
}
