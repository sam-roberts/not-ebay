package ass2;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FormManager {


	//different restraints
	public static final int RESTRICT_NONE = 0;
	public static final int RESTRICT_ALPHHANUMERIC_NOSPACE = 1;
	public static final int RESTRICT_ALPHHANUMERIC_SPACE = 2;
	public static final int RESTRICT_EMAIL = 3;
	public static final int RESTRICT_NUMERIC_ONLY = 4;	
	public static final int RESTRICT_FLOAT_ONLY = 5;



	private static final String ERROR_MISSING = "field is empty";
	private static final String ERROR_INVALID_ALPHANUMERIC = "field should contain only alphanumeric values";
	private static final String ERROR_INVALID_EMAIL = "email address provided is invalid";
	private static final String ERROR_NUMERIC_ONLY = "field must contain numbers only";
	
	private static final String ERROR_FLOAT_ONLY = "field must be a positive number";


	Map<String, Form> forms;

	// true = THERE IS A PROBLEM, false = everythings ok

	public FormManager() {
		forms = new HashMap<String, Form>();
	}

	public void addForm(String key, String value) {
		forms.put(key, new Form(key, value));
	}

	public void addForm(String key, String value, int restrictionType) {
		Form f = new Form(key,value);
		f.setRestrictionType(restrictionType);
		forms.put(key, f);
	}
	public boolean isMissingDetails() {

		//Work out if everything is correct
		for (String key: forms.keySet()) {
			Form thisForm = forms.get(key);
			if (thisForm.getValue().equals("")) {

				// if you set something invalid, you must also set an error message!!
				thisForm.setInvalid(true);
				thisForm.setErrorMessage(ERROR_MISSING);

			}

			//if its missing information, it doesn't matter if it doesn't look right.
			if (thisForm.getInvalid() == false) {
				if (thisForm.getRestrictionType() != RESTRICT_NONE) {
					if (thisForm.getValue().toLowerCase().matches(getPatternFromType(thisForm.getRestrictionType())) == false) {
						thisForm.setInvalid(true);
						if (thisForm.getRestrictionType() == RESTRICT_ALPHHANUMERIC_NOSPACE) {
							thisForm.setErrorMessage(ERROR_INVALID_ALPHANUMERIC);
						} else if (thisForm.getRestrictionType() == RESTRICT_EMAIL) {
							thisForm.setErrorMessage(ERROR_INVALID_EMAIL);
						} else if (thisForm.getRestrictionType() == RESTRICT_NUMERIC_ONLY) {
							thisForm.setErrorMessage(ERROR_NUMERIC_ONLY);
						} else if (thisForm.getRestrictionType() == RESTRICT_FLOAT_ONLY) {
							thisForm.setErrorMessage(ERROR_FLOAT_ONLY);
						}
					}
				}
			}
		}


		//actually see if anything is incorrect
		for (String key : forms.keySet()) {
			if (forms.get(key).getInvalid() == true) {
				return true;
			}
		}
		return false;
	}

	private String getPatternFromType(int restrictionType) {
		if (restrictionType == RESTRICT_ALPHHANUMERIC_NOSPACE) {
			return "[a-zA-Z\\d]+";
		} else if (restrictionType == RESTRICT_ALPHHANUMERIC_SPACE) {
			return "[a-zA-Z\\d ]+";

		} else if (restrictionType == RESTRICT_EMAIL) {
			return "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
		} else if (restrictionType == RESTRICT_NUMERIC_ONLY) {
			return "\\d+";
		} else if (restrictionType == RESTRICT_FLOAT_ONLY) {
			return "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$";
		}
		return null;
	}

	public String getMessage() {
		StringBuilder s = new StringBuilder();
		for (String key: forms.keySet()) {
			Form thisForm = forms.get(key);
			if (thisForm.getInvalid() == true) {
				s.append(key + " " + thisForm.getErrorMessage() + ".<br />\n");
			}
		}
		return s.toString();

	}

	/** Do not change the key, simple change the value for a given key - Mainly used for debugging */
	public void setForm(String key, String value) {
		if (forms.containsKey(key)) {
			forms.get(key).setValue(value);
			forms.get(key).setInvalid(false);
		}
	}


}
