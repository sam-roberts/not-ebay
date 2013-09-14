package unitTests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import ass2.FormManager;
import ass2.JDBCConnector;

public class DatabaseTester {
	
	JDBCConnector jdbc;
	@Before
    public void setUp() throws Exception {
		jdbc = new JDBCConnector();
    }
	
	
	/*
	 * 		//tests for jdbc
		JDBCConnector jdbc = new JDBCConnector();
		jdbc.addUser("a", "a", "a", "a", "a", "a", 9, "a", 9, false);
		jdbc.addAuction("a", "a", "a", "a", "a", "a", 9, 5, 5, new Timestamp(0), false);
		jdbc.addBidding("a", 1, 5,new Timestamp(0));
		jdbc.addUser("b", "a", "a", "a", "a", "a", 9, "a", 9, false);
		jdbc.addAuction("a", "b", "a", "a", "a", "a", 9, 5, 5, new Timestamp(0), false);
		jdbc.addBidding("b", 2, 5,new Timestamp(0));
		System.out.println(jdbc.userExists("a"));
		jdbc.getUserBean("a");
	 */
	
	
	@Test
	public void testCreateAccount() {
		//jdbc.addUser(username, password, email, nickname, firstName, lastName, yearOfBirth, postalAddress, CCNumber, banned);
		jdbc.addUser("user1", "coolpassword", "a", "a", "a", "a", 9, "a", 9, false);
		jdbc.addUser("example", "example", "a", "a", "a", "a", 1901, "address", 1, false);
		
		assertTrue(jdbc.isUserExists("user1"));
		assertTrue(jdbc.isUserExists("example"));
		
		assertFalse(jdbc.isUserExists("user2"));
		assertFalse(jdbc.isUserExists("notreal09account"));
		
	}
}
