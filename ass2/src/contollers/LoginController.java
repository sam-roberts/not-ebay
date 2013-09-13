package contollers;

import ass2.FormManager;
import ass2.ParameterManager;

public class LoginController extends MasterFormBasedController {

	
	FormManager formManager;
	public LoginController(ParameterManager params) {
		super(params);
		formManager = new FormManager();
		
		createForm();
		
		// TODO Auto-generated constructor stub
	}

	private void createForm() {
		formManager.addForm("username", paramManager.getIndividualParam("username"),FormManager.RESTRICT_ALPHHANUMERIC_NOSPACE);
		formManager.addForm("password", paramManager.getIndividualParam("password"));	
	}
	
	public boolean isInvalidForm() {
		return formManager.isMissingDetails();
	}
	
	public String getMessage() {
		return formManager.getMessage();
	}

	public boolean isValidAccount() {
		// TODO Auto-generated method stub
		Boolean valid = true;
		if (valid) {
			message = "Invalid Username/Password";
		} else {
			message = "Success!";
		}
		return valid;
	}

}
