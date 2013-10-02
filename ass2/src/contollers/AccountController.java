package contollers;

import java.util.ArrayList;

import beans.UserBean;
import ass2.FormManager;
import ass2.JDBCConnector;
import ass2.ParameterManager;

public class AccountController extends MasterFormBasedController {

	ArrayList<String> thingsChanged;
	public AccountController(ParameterManager params) {
		super(params);
		thingsChanged = new ArrayList<String>();
	}

	protected void createForm() {

		formManager.addOptionalForm("password", paramManager.getIndividualParam("password"));
		formManager.addOptionalForm("email", paramManager.getIndividualParam("email"), FormManager.RESTRICT_EMAIL);
		formManager.addOptionalForm("nickname", paramManager.getIndividualParam("nickname"));
		formManager.addOptionalForm("firstName", paramManager.getIndividualParam("firstName"));
		formManager.addOptionalForm("lastName", paramManager.getIndividualParam("lastName"));
		formManager.addOptionalForm("yearOfBirth", paramManager.getIndividualParam("yearOfBirth"), FormManager.RESTRICT_NUMERIC_ONLY);
		formManager.addOptionalForm("address", paramManager.getIndividualParam("address"));
		formManager.addOptionalForm("ccNumber", paramManager.getIndividualParam("ccNumber"), FormManager.RESTRICT_NUMERIC_ONLY);
	}

	public UserBean updateAccount(String username, boolean isAdminLogin) {
		UserBean ub = new UserBean();
		ub.setUsername(username);
		ub.setPassword(paramManager.getIndividualParam("password"));
		ub.setEmail(paramManager.getIndividualParam("email"));
		ub.setNickname(paramManager.getIndividualParam("nickname"));
		ub.setFirstName(paramManager.getIndividualParam("firstName"));
		ub.setLastName(paramManager.getIndividualParam("lastName"));
		if (!"".equals(paramManager.getIndividualParam("yearOfBirth")))
			ub.setYearOfBirth(Integer.parseInt(paramManager.getIndividualParam("yearOfBirth")));
		ub.setPostalAddress(paramManager.getIndividualParam("address"));
		if (!"".equals(paramManager.getIndividualParam("ccNumber")))
			ub.setCcNumber(Integer.parseInt(paramManager.getIndividualParam("ccNumber")));
		JDBCConnector.updateAccount(ub);
		return JDBCConnector.getUserBean(username, isAdminLogin);
	}
	public boolean hasChanged() {
		return thingsChanged.size() > 0;
	}
	public String getChangedDetails() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < thingsChanged.size(); i++) {
			s.append(thingsChanged.get(i));

			if (i > 0 && i < thingsChanged.size()) {
				s.append(", ");
			}
		}

		return s.toString();
	}

}
