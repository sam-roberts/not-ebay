package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ass2.FormManager;
import ass2.ParameterManager;

public class Forms_AddAuctionTester {
	
	FormManager f;
	@Before
	public void setUp() throws Exception {
		f = new FormManager();
	}

	@Test
	public void allValidTest() {
		f.addForm("title", "20013 New Bike");
		f.addForm("category", "bikes");
		f.addForm("picture", "somepath/bike.jpg");
		f.addForm("description", "clean, sport, amazing");
		f.addForm("postageDetails", "1 High Street, UNSW");
		f.addForm("reservePrice", "50",FormManager.RESTRICT_FLOAT_ONLY);
		f.addForm("biddingStart","20.25", FormManager.RESTRICT_FLOAT_ONLY);
		f.addForm("auctionEnd", "30", FormManager.RESTRICT_NUMERIC_ONLY); //how many minutes from now
		f.addForm("biddingIncrements", "0.50", FormManager.RESTRICT_FLOAT_ONLY);
		
		assertFalse(f.isMissingDetails());
	}

}
