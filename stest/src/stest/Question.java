package stest;

import java.util.ArrayList;
import java.util.List;

public class Question {
	public int id;
	public String questionText;
	public List<String> answers = new ArrayList<String>();
	public List<String> possibleAnswers = new ArrayList<String>();
	
	public void writeQuestion() {
		System.out.println("Question id: " + this.id);
		System.out.println("Question Text: " + this.questionText);
		System.out.println("Possible Answers: ");
		for(String s: this.possibleAnswers) {
			System.out.println(s + " ");
		}
		System.out.println("Correct Answers: ");
		for(String s: this.answers) {
			System.out.println(s);
		}
	}
}
