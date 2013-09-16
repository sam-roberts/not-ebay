package contollers;

import beans.UserBean;
import ass2.FormManager;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class AddAuctionController extends MasterFormBasedController{

	public AddAuctionController(ParameterManager params) {
		super(params);
	}

	protected void createForm() {
		/*
		<li>Title: <input type="text" name="title"></li>
		<li>Category: <input type="password" name="category"></li>
		<li>Picture: <input type="file" name="picture"></li>
		<li>Description: <input type="text" name="description" width=600 height=300></li>
		<li>Postage Details: <input type="text" name="postageDetails"></li>
		<li>Reserve Price: <input type="text" name="reservePrice"></li> 	
		<li>Bidding Start Price: <input type="text" name="biddingStart"></li>
		<li>Bidding Increments: <input type="text" name="biddingIncrements"></li>
		<li>End of Auction: <input type="text" name="endOfAuction"></li>
		<li><input type="submit" value="submit"></li>
		*/
		formManager.addForm("title", paramManager.getIndividualParam("title"));
		formManager.addForm("category", paramManager.getIndividualParam("category"));
		formManager.addForm("picture", paramManager.getIndividualParam("picture"));
		formManager.addForm("description", paramManager.getIndividualParam("description"));
		formManager.addForm("postageDetails", paramManager.getIndividualParam("postageDetails"));
		formManager.addForm("reservePrice", paramManager.getIndividualParam("reservePrice"),FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addForm("biddingStart", paramManager.getIndividualParam("biddingStart"), FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addForm("biddingIncrements", paramManager.getIndividualParam("biddingIncrements"), FormManager.RESTRICT_NUMERIC_ONLY);
		// it's optional so i think don't add it
		//formManager.addForm("endOfAuction", paramManager.getIndividualParam("endOfAuction"));
	}
	
	public void addAuction() {

	}
	
}
