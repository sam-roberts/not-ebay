package contollers;

import beans.UserBean;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class LoginController extends MasterFormBasedController {

	//note we also have ParameterManager paramManager
	
	public LoginController(ParameterManager params) {
		super(params);
	}

	protected void createForm() {
		formManager.addForm("username", paramManager.getIndividualParam("username"));
		formManager.addForm("password", paramManager.getIndividualParam("password"));	
	}

	public UserBean requestLogin(boolean isAdminLogin) {
		if (JDBCConnector.hasLogin(paramManager.getIndividualParam("username"), paramManager.getIndividualParam("password"))) {
			message = "Success!";
			return JDBCConnector.getUserBean(paramManager.getIndividualParam("username"), isAdminLogin);
		} else {
			message = "Invalid Username/Password!";
		}
		return null;
	}

}
