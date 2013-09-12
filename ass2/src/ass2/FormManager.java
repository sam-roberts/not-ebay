package ass2;

import java.util.HashMap;
import java.util.Map;

public class FormManager {

	Map<String, String> forms;
	public FormManager() {
		forms = new HashMap<String, String>();
	}
	
	public void addForm(String key, String value) {
		forms.put(key, value);
	}
	public boolean isMissingDetails() {
		for (String key: forms.keySet()) {
			if (forms.get(key).equals("")) {
				return true;
			}
		}
		return false;
	}
	
	public String getMessage() {
		StringBuilder s = new StringBuilder();
		s.append("Missing:");
		for (String key: forms.keySet()) {
			if (forms.get(key).equals("")) {
				s.append(" " + key);
			}
		}
		s.append(".");
		return s.toString();
		
	}
}
