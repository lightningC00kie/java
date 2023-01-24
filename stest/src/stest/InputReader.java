package stest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputReader {
	public static Scanner scanner = new Scanner(System.in);
	static Map<Integer, Question> questions = new HashMap<Integer, Question>();
	static List<Answer> answers = new ArrayList<Answer>();
	
	public static Question readQuestion() {
		String questionRegex =  "^\\d+\\.\\s.*";
		String answerRegex = "[A-Z]\\. .*";
		Question q = new Question();
		int id;
		String questionText;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.strip().matches(questionRegex)) {
            	String question = input.strip();
            	id = Integer.parseInt(String.valueOf(question.charAt(0)));
            	questionText = question.substring(2).strip(); 
            	q.id = id;
            	q.questionText = questionText;
            }
            else if(input.strip().matches(answerRegex)) {
            	String answer = input.strip();
            	q.possibleAnswers.add(String.valueOf(answer.charAt(0)));
            }
            else if(input.strip().split("\\s+")[0].equals("Answer:")) {
            	String[] answers = input.strip().split("\\s+");
            	for (int i = 1; i < answers.length; i++) {
            		q.answers.add(answers[i]);
            	}
            }
            else if (input.strip().equals("")) {
            	break;
            }
            else {
//            	break;
            	continue;
            }
        }
        return q;
	}
	
	public static Answer readAnswer() {
		String answerRegex =  "^\\d+\\.\\s.*";
		Answer a = new Answer();
		while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
//            System.out.println(input);
            if (input.strip().matches(answerRegex)) {
            	String[] answers = input.strip().split("//s+");
            	a.id = Integer.parseInt(String.valueOf(answers[0].charAt(0)));
            	for (int i = 1; i < answers.length; i++) {
            		System.out.println("answer:" + answers[i]);
            		a.answers.add(answers[i]);
            	}
            }
            else if (input.strip().equals("")) {
            	break;
            }
            else {
            	System.out.println("empty line");
            	break;
            }
        }
		return a;
	}
	
	public static void read() {
		while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.strip().equals("")) {
            	continue;
            }
            else if (input.strip().equals("multichoice") || input.strip().equals("singlechoice")) {
            	System.out.println("Reading a question");
            	Question q = readQuestion();
            	q.writeQuestion();
            	questions.put(q.id, q);
            }
            else {
            	System.out.println("Reading an answer");
            	String name = input.strip();
            	Answer a = readAnswer();
            	a.name = name;
            	answers.add(a);
            	a.writeAnswer();
            }
        }
	}
	
	public static Map<Integer, Question> getQuestions() {
		return questions;
	}
	
	public static List<Answer> getAnswers() {
		return answers;
	}
}
