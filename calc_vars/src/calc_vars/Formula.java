package calc_vars;

import java.util.EmptyStackException;
import java.util.Stack;

public class Formula {
	
	private static Double evaluateFormula (String op, String operand1, String operand2) {
		if (op.equals("+")) {
			return Double.parseDouble(operand1) + Double.parseDouble(operand2);
		}
		if (op.equals("*")) {
			return Double.parseDouble(operand1) * Double.parseDouble(operand2);
		}
		if (op.equals("-")) {
			return Double.parseDouble(operand1) - Double.parseDouble(operand2);
		}
		if (op.equals("/") && Double.parseDouble(operand2) != 0) {
			return Double.parseDouble(operand1) / Double.parseDouble(operand2);
		}
		return null;
	}
	
	private static boolean isScientificNotation(String expr) {
	    String pattern = "^[+-]?[0-9]+(\\.[0-9]+)?(e[+-]?[0-9]+)?$";
	    return expr.matches(pattern);
	}
	
	private static boolean isOperator(String c) {
        return (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/"));
    }
    private static int precedence(String c) {
        switch (c) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
        }
        return -1;
    }
    
    public static String evaluateInfix(String infix) {
        Stack<String> opStack = new Stack<String>();
        Stack<String> numStack = new Stack<String>();
        int i = 0;
        while (i < infix.length()) {
        	if (Character.isDigit(infix.charAt(i))){
        		// if its a number push it to the numStack
        		String num = extractNum(infix, i);
        		if (num == null) {
        			return "ERROR";
        		}
        		if (opStack.size() > 0 && opStack.peek().equals('-' + "") && numStack.size() == 0) {
        			numStack.push("-" + num);
        			opStack.pop();
        		}
        		else {
            		numStack.push(extractNum(infix, i));
        		}
        		i += extractNum(infix, i).length();
        		continue;
        	}
        	if (Character.isLetter(infix.charAt(i))) {
        		// get variable value and push onto numStack
        		numStack.push(String.valueOf(Variable.getVariable(extractVar(infix, i))));
        		i += extractVar(infix, i).length();
        		continue;
        	}
        	if (infix.charAt(i) == '(') {
        		opStack.push(infix.charAt(i) + "");
        	}
        	
        	if (infix.charAt(i) == ')') {
        		try {
	        		while (!opStack.peek().equals('(' + "")) {
	        			String op = opStack.pop();
	        			String operand2 = numStack.pop();
	        			String operand1 = numStack.pop();
	        			numStack.push(String.valueOf(evaluateFormula(op, operand1, operand2)));
	        		}
        		}
        		catch (EmptyStackException e) {
    				return "ERROR";
    			}
        		opStack.pop();
        	}
        	
        	if (isOperator(infix.charAt(i) + "")) {
        		while (opStack.size() > 0 && precedence(opStack.peek()) >= precedence(infix.charAt(i) + "")) {
        			try {
        				String op = opStack.pop();
            			String operand2 = numStack.pop();
            			String operand1 = numStack.pop();
            			numStack.push(String.valueOf(evaluateFormula(op, operand1, operand2)));
        			}
        			catch (EmptyStackException e) {
        				return "ERROR";
        			}
        		}
        		opStack.push(infix.charAt(i) + "");
        	}
        	i++;
        }
        
        if (opStack.size() == 1 && opStack.peek().equals("-") && numStack.size() == 1) {
        	return "-" + numStack.pop();
        }
        
        while (opStack.size() > 0) {
        	try {
        	String op = opStack.pop();
			String operand2 = numStack.pop();
			String operand1 = numStack.pop();
			numStack.push(String.valueOf(evaluateFormula(op, operand1, operand2)));
        	}
        	catch (EmptyStackException e) {
				return "ERROR";
			}
        }
        
        if (opStack.size() != 0 || numStack.size() != 1) {
        	return "ERROR";
        }
        
        return numStack.pop();
    }
    
    private static String extractNum(String s, int index) {
    	String num = "";
    	boolean isScientific = false;
    	if (index >= s.length()) {
    		return num;
    	}
    	for (int i = index; i < s.length(); i++) {
    		if (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.' || s.charAt(i) == 'e' || (s.charAt(i) == '-' && isScientific)) {
    			if (s.charAt(i) == 'e') {
    				isScientific = true;
    			}
    			num += s.charAt(i);
    			continue;
    		}
    		break;
    	}
    	
    	if (isScientific && !isScientificNotation(num)) {
    		System.out.println(num);
    		return null;
    	}
    	
    	return num;
    }
    
    private static String extractVar(String s, int index) {
    	String var = "";
    	if (index >= s.length()) {
    		return var;
    	}
    	for (int i = index; i < s.length(); i++) {
    		if (Character.isLetter(s.charAt(i))) {
    			var += s.charAt(i);
    			continue;
    		}
    		break;
    	}
    	return var;
    }
}
