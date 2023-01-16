package calc_vars;

import java.util.HashMap;
import java.util.Map;

public class Variable {
	private static Map<String, Double> variables = new HashMap<String, Double>();
	
	public static double getVariable(String var) {
		if (variables.containsKey(var)) {
			return variables.get(var);
		}
		return 0.0;
	}
	
	public static void setVariable(String var, Double val) {
		variables.put(var, val);
	}
	
	public static void writeMap() {
		for(String key : variables.keySet()) {
			System.out.println(key + ": " + variables.get(key));
		}
	}
}
