package calc_vars;

import java.util.Scanner;

public class Top {
	public static void main(String[] args) {
		readStdin();
	}
	
	public static void readStdin() {
		try (Scanner sc = new Scanner(System.in)) {
			while (sc.hasNextLine()) {
			  String line = sc.nextLine();
			  processLine(line);
			}
		}
	}
	
	public static void processLine(String line) {
		String assignmentRegex = "\\w+\\s*=\\s*[\\w\\d\\+\\-\\*/\\(\\)\\s]+";
		String variableRegex = "\\b[a-zA-Z]+\\b";
		if (line.strip().equals("")) {
			return;
		}
		if (line.matches(assignmentRegex)) {
			String[] assignment = line.split("=");
			double res = Double.parseDouble(Formula.evaluateInfix(assignment[1]));
			Variable.setVariable(assignment[0].strip(), res);
			Variable.setVariable("last", res);
			System.out.printf("%.5f", res);
			System.out.println();
		}
		else if (line.matches(variableRegex)) {
			System.out.printf("%.5f", Variable.getVariable(line.strip()));
			System.out.println();
		}
		else {
			String res = Formula.evaluateInfix(line.strip());
			if (res != null && res != "ERROR") {
				System.out.printf("%.5f", Double.parseDouble(res));
				System.out.println();
				Variable.setVariable("last", Double.parseDouble(res));
				return;
			}
			System.out.println(res);
		}
	}
}
