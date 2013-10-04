package ass2;

import java.nio.MappedByteBuffer;
import java.util.Map;

public class ParameterManager {

	private Map<String, String[]> map;
	public ParameterManager(Map<String, String[]> map) {
		this.map = map;
		
		for(String keys: map.keySet()) {
			int i = 0;
			for (String value: map.get(keys)) {
				value = value.replaceAll("<", "&lt;");
				value = value.replaceAll(">", "&gt;");
				map.get(keys)[i] = value;
				i++;
			}
		}
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
	
	public Map<String, String[]> getMap() {
		return this.map;
	}

	public void printAllValues() {
		for(String keys: map.keySet()) {
			System.out.print("Control Servlet Parameter Key: [ " + keys + " ] Parameter Values: [");
			int i = 0;
			for (String value: map.get(keys)) {
				if (i > 0) {
					System.out.print("(" + i + ") " + value);
				} else {
					System.out.print(" " + value);
				}
				i++;
			}

			System.out.println(" ]");
		}
	}
}
