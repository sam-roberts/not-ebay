package contollers;

import beans.UserBean;
import beans.UserListBean;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class LoginController extends MasterFormBasedController {

	//note we also have ParameterManager paramManager
	
	
	
	public LoginController(ParameterManager params) {
		super(params);
	}

	protected void createForm() {
		if (paramManager.hasParameter("username")) formManager.addForm("username", paramManager.getIndividualParam("username"));
		if (paramManager.hasParameter("password")) formManager.addForm("password", paramManager.getIndividualParam("password"));	
	}

	public UserBean requestLogin(boolean isAdminLogin) {
		if (JDBCConnector.hasLogin(paramManager.getIndividualParam("username"), paramManager.getIndividualParam("password"))) {
			message = "Success!";
			UserBean ub = JDBCConnector.getUserBean(paramManager.getIndividualParam("username"), isAdminLogin);
			if (isAdminLogin) {
				if (ub.getIsAdmin()) {
					message = "Success!";
					return ub;
				} else
					message = "Not an admin account";
			} else {
				message = "Success!";
				return ub;
			}
		} else {
			message = "Invalid Username/Password!";
		}
		return null;
	}
	
	public UserListBean getUsers() {
		return JDBCConnector.getUsers();
	}
	
	public void banUsers() {
		JDBCConnector.banUser(paramManager.getIndividualParam("username"));
	}
	
	public void verify() {
		if (!paramManager.hasParameter("hash") || !paramManager.hasParameter("username")) {
			message="Verification is Missing username or hash";
			return;
		}
		
		if (JDBCConnector.checkVerification(paramManager.getIndividualParam("username"), Integer.parseInt(paramManager.getIndividualParam("hash")))) {
			JDBCConnector.verify(paramManager.getIndividualParam("username"));
			message="Congratulations, You have been verified. You may log in now";
		} else {
			//verification failed
			message="Incorrect verificaiton hash, or you may already be verified.";
		}
	}

}
