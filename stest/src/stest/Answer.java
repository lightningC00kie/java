package stest;

import java.util.List;

public class Answer {
	public String name;
	public int id;
	public List<String> answers;
	
	public void writeAnswer() {
		System.out.println("Name: " + this.name);
		System.out.println("Question ID: " + this.id);
		for (String s:this.answers) {
			System.out.println(s);
		}
	}
}
