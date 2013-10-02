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

	public void updateAccount(UserBean ub) {
		if (ub != null) {
			if (formManager.isValueBlank("password") == false) {
				ub.setPassword(paramManager.getIndividualParam("password"));
				thingsChanged.add("password");

			}

			if (formManager.isValueBlank("email") == false) {
				ub.setEmail(paramManager.getIndividualParam("email"));
				thingsChanged.add("email");


			}

			if (formManager.isValueBlank("nickname") == false) {
				ub.setNickname(paramManager.getIndividualParam("nickname"));
				thingsChanged.add("nickname");


			}

			if (formManager.isValueBlank("firstName") == false) {
				ub.setFirstName(paramManager.getIndividualParam("firstName"));
				thingsChanged.add("first name");


			}

			if (formManager.isValueBlank("lastName") == false) {
				ub.setLastName(paramManager.getIndividualParam("lastName"));
				thingsChanged.add("last name");


			}

			if (formManager.isValueBlank("yearOfBirth") == false) {
				ub.setYearOfBirth(Integer.parseInt(paramManager.getIndividualParam("yearOfBirth")));
				thingsChanged.add("year of birth");


			}

			if (formManager.isValueBlank("address") == false) {
				ub.setPostalAddress(paramManager.getIndividualParam("address"));
				thingsChanged.add("address");


			}

			if (formManager.isValueBlank("ccNumber") == false) {
				ub.setCcNumber(Integer.parseInt(paramManager.getIndividualParam("ccNumber")));
				thingsChanged.add("credit card number");


			}
			JDBCConnector.updateAccount(ub);
		} else {
			System.out.println("User is null, i'm not going to update the account");
		}
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
