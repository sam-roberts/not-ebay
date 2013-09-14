package contollers;

import ass2.FormManager;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class RegistrationController extends MasterFormBasedController {
	FormManager formManager;
	public RegistrationController(ParameterManager params) {
		super(params);
		formManager = new FormManager();
		
		createForm();
		
		// TODO Auto-generated constructor stub
	}

	private void createForm() {
		formManager.addForm("username", paramManager.getIndividualParam("username"),FormManager.RESTRICT_ALPHHANUMERIC_NOSPACE);
		formManager.addForm("password", paramManager.getIndividualParam("password"));
		formManager.addForm("email", paramManager.getIndividualParam("email"), FormManager.RESTRICT_EMAIL);
		formManager.addForm("nickname", paramManager.getIndividualParam("nickname"));
		formManager.addForm("firstName", paramManager.getIndividualParam("firstName"));
		formManager.addForm("lastName", paramManager.getIndividualParam("lastName"));
		formManager.addForm("yearOfBirth", paramManager.getIndividualParam("yearOfBirth"), FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addForm("address", paramManager.getIndividualParam("address"));
		formManager.addForm("ccNumber", paramManager.getIndividualParam("ccNumber"), FormManager.RESTRICT_NUMERIC_ONLY);

		




	}

	public boolean isInvalidForm() {
		return formManager.isMissingDetails();
	}

	public String getMessage() {
		return formManager.getMessage();
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
	public void registerUser() {
		String username = paramManager.getIndividualParam("username");
		String password = paramManager.getIndividualParam("password"); 
		String email = paramManager.getIndividualParam("email");
		String nickname= paramManager.getIndividualParam("nickname"); 
		String firstName = paramManager.getIndividualParam("firstName"); 
		String lastName = paramManager.getIndividualParam("lastName");
		int yearOfBirth = Integer.parseInt(paramManager.getIndividualParam("yearOfBirth")); 
		String postalAddress= paramManager.getIndividualParam("address");
		int CCNumber = Integer.parseInt(paramManager.getIndividualParam("ccNumber"));
		JDBCConnector.addUser(username, password, email, nickname, firstName, lastName, yearOfBirth, postalAddress, CCNumber, false);
	}
}
