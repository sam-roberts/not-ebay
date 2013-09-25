package contollers;

import beans.UserBean;
import ass2.FormManager;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class AccountController extends MasterFormBasedController {
	
	public AccountController(ParameterManager params) {
		super(params);
	}

	protected void createForm() {
		formManager.addForm("password", paramManager.getIndividualParam("password"));
		formManager.addForm("email", paramManager.getIndividualParam("email"), FormManager.RESTRICT_EMAIL);
		formManager.addForm("nickname", paramManager.getIndividualParam("nickname"));
		formManager.addForm("firstName", paramManager.getIndividualParam("firstName"));
		formManager.addForm("lastName", paramManager.getIndividualParam("lastName"));
		formManager.addForm("yearOfBirth", paramManager.getIndividualParam("yearOfBirth"), FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addForm("address", paramManager.getIndividualParam("address"));
		formManager.addForm("ccNumber", paramManager.getIndividualParam("ccNumber"), FormManager.RESTRICT_NUMERIC_ONLY);
	}

	public void updateAccount(String username) {
		UserBean ub = new UserBean();
		ub.setUsername(username);
		ub.setPassword(paramManager.getIndividualParam("password"));
		ub.setEmail(paramManager.getIndividualParam("email"));
		ub.setNickname(paramManager.getIndividualParam("nickname"));
		ub.setFirstName(paramManager.getIndividualParam("firstName"));
		ub.setLastName(paramManager.getIndividualParam("lastName"));
		ub.setYearOfBirth(Integer.parseInt(paramManager.getIndividualParam("yearOfBirth")));
		ub.setPostalAddress(paramManager.getIndividualParam("address"));
		ub.setCcNumber(Integer.parseInt(paramManager.getIndividualParam("ccNumber")));
		JDBCConnector.updateAccount(ub);
	}
	
}
