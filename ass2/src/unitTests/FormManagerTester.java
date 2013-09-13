package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ass2.FormManager;

public class FormManagerTester {
	FormManager fm;
	
	
	@Before
    public void setUp() throws Exception {
		fm = new FormManager();
    }
	@Test
	public void testNoMissingParameters() {
		fm.addForm("action", "search");
		fm.addForm("username", "coolman");
		assertFalse(fm.isMissingDetails());
	}
	@Test
	public void testIsMissingParameters() {
		fm.addForm("action", "search");
		fm.addForm("username", "");
		fm.addForm("password", "blah");

		assertTrue(fm.isMissingDetails());
	}
	
	@Test
	public void testInvalidUsernameSpace() {
		fm.addForm("username", "cool man", FormManager.RESTRICT_ALPHHANUMERIC_NOSPACE);
		assertTrue(fm.isMissingDetails());
	}
	
	@Test
	public void testInvalidUsernameGarbage() {
		fm.addForm("username", "$^$*(oihjdojhjaej209", FormManager.RESTRICT_ALPHHANUMERIC_NOSPACE);
		assertTrue(fm.isMissingDetails());
	}
	@Test
	public void testValidUsername1() {
		fm.addForm("username", "auctioneer123", FormManager.RESTRICT_ALPHHANUMERIC_NOSPACE);
		assertFalse(fm.isMissingDetails());
		
		fm.setForm("username", "MaNiAC");
		assertFalse(fm.isMissingDetails());
		
		fm.setForm("username", "36454548");
		assertFalse(fm.isMissingDetails());


	}
	
	
	

}
