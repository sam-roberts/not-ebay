package ass2;

import java.util.Map;

public class ParameterManager {

	private Map<String, String[]> map;
	public ParameterManager(Map<String, String[]> map) {
		this.map = map;
	}

	public boolean hasParameter(String whichParameter) {
		return map.containsKey(whichParameter);
	}

	//PLEASE USE hasParameter before using these
	public String getIndividualParam(String whichParameter) {
		return map.get(whichParameter)[0];
	}

	public String[] getMultipleParam(String whichParameter) {
		return map.get(whichParameter);

	}

	public void printAllValues() {
		for(String keys: map.keySet()) {
			System.out.print("Control Servlet Parameter Key: [ " + keys + " ] Parameter Values: [");
			int i = 0;
			for (String values: map.get(keys)) {
				if (i > 0) {
				System.out.print("(" + i + ") " + values);
				} else {
					System.out.print(" " + values);

				}
				i++;
			}

			System.out.println(" ]");
		}
	}
}
