package beans;

public class UserBeanHelper {
	UserBean ub;

	public UserBeanHelper(UserBean ub) {
		this.ub = ub;
	}

	public String getStringAttributeFromName(String which) {
		if (which.equals("password")) {
			return ub.getPassword();
		} else if (which.equals("email")) {
			return ub.getEmail();
		} else if (which.equals("nickname")) {
			return ub.getNickname();
		} else if (which.equals("firstName")) {
			return ub.getFirstName();
		} else if (which.equals("lastName")) {
			return ub.getLastName();

		} else if (which.equals("address")) {
			return ub.getPostalAddress();

		}

		return null;
	}

	public void setStringAttributeFromName(String which, String value) {
		if (which.equals("password")) {
			ub.setPassword(value);
		} else if (which.equals("email")) {
			ub.setEmail(value);
		} else if (which.equals("nickname")) {
			ub.setNickname(value);
		} else if (which.equals("firstName")) {
			ub.setFirstName(value);
		} else if (which.equals("lastName")) {
			ub.setLastName(value);
		} else if (which.equals("address")) {
			ub.setPostalAddress(value);

		}
	}

	public int getIntAttributeFromName(String which) {
		// TODO Auto-generated method stub
		if (which.equals("yearOfBirth")) {
			return ub.getYearOfBirth();
		} else if (which.equals("ccNumber")) {
			return ub.getCcNumber();
		}
		System.out.println("using getIntAttributeFromName wrong!!");
		return 0;
	}
	
	public void setIntAttributeFromName(String which, int amount) {
		// TODO Auto-generated method stub
		if (which.equals("yearOfBirth")) {
			ub.setYearOfBirth(amount);
		} else if (which.equals("ccNumber")) {
			ub.setCcNumber(amount);
		}
		System.out.println("using setIntAttributeFromName wrong!!");
	}


}
