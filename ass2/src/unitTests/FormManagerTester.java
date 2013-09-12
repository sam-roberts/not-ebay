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
	
	public void testIsMissingParameters() {
		fm.addForm("action", "search");
		fm.addForm("username", "");
		fm.addForm("password", "blah");

		assertTrue(fm.isMissingDetails());
	}

}
