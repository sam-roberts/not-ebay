package contollers;

import ass2.Emailer;
import ass2.FormManager;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class RegistrationController extends MasterFormBasedController {

	public RegistrationController(ParameterManager params) {
		super(params);
	}

	protected void createForm() {
		formManager.addForm("username", paramManager.getIndividualParam("username"),FormManager.RESTRICT_ALPHHANUMERIC_NOSPACE);
		formManager.addForm("password", paramManager.getIndividualParam("password"));
		formManager.addForm("email", paramManager.getIndividualParam("email"), FormManager.RESTRICT_EMAIL);
		formManager.addOptionalForm("nickname", paramManager.getIndividualParam("nickname"));
		formManager.addOptionalForm("firstName", paramManager.getIndividualParam("firstName"));
		formManager.addOptionalForm("lastName", paramManager.getIndividualParam("lastName"));
		formManager.addOptionalForm("yearOfBirth", paramManager.getIndividualParam("yearOfBirth"), FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addOptionalForm("address", paramManager.getIndividualParam("address"));
		formManager.addOptionalForm("ccNumber", paramManager.getIndividualParam("ccNumber"), FormManager.RESTRICT_NUMERIC_ONLY);
	}

	public boolean isAccountAlreadyExists() {
		boolean exists = JDBCConnector.isUserExists(paramManager.getIndividualParam("username"));
		if (exists) {
			message = "An account with that username already exists";
		} else {
			message = "Success!";
		}
		return exists;
	}

	//fix so JDBC returns val if something goes wrong.
	public void registerUser(String url) {
		String username = paramManager.getIndividualParam("username");
		String password = paramManager.getIndividualParam("password"); 
		String email = paramManager.getIndividualParam("email");
		String nickname= paramManager.getIndividualParam("nickname"); 
		String firstName = paramManager.getIndividualParam("firstName"); 
		String lastName = paramManager.getIndividualParam("lastName");
		int yearOfBirth;
		try {
			yearOfBirth = Integer.parseInt(paramManager.getIndividualParam("yearOfBirth"));
		} catch (Exception e) {
			yearOfBirth = 0;
		}
		String postalAddress= paramManager.getIndividualParam("address");
		int CCNumber;

		try {
			CCNumber = Integer.parseInt(paramManager.getIndividualParam("ccNumber"));
		} catch (Exception e) {
			CCNumber = 0;
		}
		int hash = username.hashCode();
		JDBCConnector.addUser(username, password, email, nickname, firstName, lastName, yearOfBirth, postalAddress, CCNumber, false, hash);
		String title = "Email activation required";
		String msg = "To activate your account, go to: " + url + "?action=verify&username=" + username + "&hash=" + hash;
		Emailer e = new Emailer(email, title, msg);
		e.email();
	}
}
