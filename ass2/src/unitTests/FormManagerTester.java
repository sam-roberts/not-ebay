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
	
	@Test
	public void testInvalidEmail() {
		fm.addForm("email", "blah", FormManager.RESTRICT_EMAIL);
		assertTrue(fm.isMissingDetails());
		
		fm.setForm("email", "e e09tjea90tj@blahoei0.com");
		assertTrue(fm.isMissingDetails());
		
	}
	
	@Test
	public void testValidEmail() {
		fm.addForm("email", "c00lguy@gmail.com", FormManager.RESTRICT_EMAIL);
		assertFalse(fm.isMissingDetails());
		
		fm.setForm("email", "z1234567@student.unsw.edu.au");
		assertFalse(fm.isMissingDetails());
		
		fm.setForm("email", "myaddress+stopspam@gmail.com");
		assertFalse(fm.isMissingDetails());
		
	}
	
	@Test
	public void testValidFloat() {
		fm.addForm("blah", "1", FormManager.RESTRICT_FLOAT_ONLY);
		assertFalse(fm.isMissingDetails());
		
		fm.setForm("blah", "1.00");
		assertFalse(fm.isMissingDetails());
		
		fm.setForm("blah", "0.00");
		assertFalse(fm.isMissingDetails());
		
		fm.setForm("blah", "100.50");
		assertFalse(fm.isMissingDetails());

		
	}
	
	@Test
	public void testInvalidFloat() {
		fm.addForm("blah", "-1", FormManager.RESTRICT_FLOAT_ONLY);
		assertTrue(fm.isMissingDetails());
		
		fm.setForm("blah", "1a.0a0");
		assertTrue(fm.isMissingDetails());
		
		fm.setForm("blah", "100.5.0");
		assertTrue(fm.isMissingDetails());

		
	}
	
	
	

}
