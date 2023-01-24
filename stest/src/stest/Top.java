package stest;

import java.util.List;
import java.util.Map;

public class Top {
	public static void main(String[] args) {
		InputReader.read();
		System.out.println("Done reading");
//		List.<Question> q = InputReader.getQuestions();
		Map<Integer, Question> q = InputReader.getQuestions();
		System.out.println(q.size());
	    q.values().forEach(v -> v.writeQuestion());
//		List<Answer> a = InputReader.getAnswers();
		
	}
}
