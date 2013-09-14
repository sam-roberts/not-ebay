package contollers;

import beans.UserBean;
import ass2.FormManager;
import ass2.JDBCConnector;
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
		formManager.addForm("username", paramManager.getIndividualParam("username"));
		formManager.addForm("password", paramManager.getIndividualParam("password"));	
	}
	
	public boolean isInvalidForm() {
		return formManager.isMissingDetails();
	}
	
	public String getMessage() {
		return formManager.getMessage();
	}

	public UserBean requestLogin(String username, String password) {
		if (JDBCConnector.login(username, password)) {
			message = "Success!";
			return JDBCConnector.getUserBean(username);
		} else {
			message = "Invalid Username/Password";
		}
		return null;
	}

}
