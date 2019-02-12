package com.quiz;

public class Quiz {
	String quizString;
	String optionA, optionB, optionC, optionD, optionE;

	public enum Answer {
		answer_A, answer_B, answer_C, answer_D, answer_E
	}

	Answer rightAswer;

	public Quiz(String quizString, String optionA, String optionB, String optionC, String optionD, String optionE,
			Answer rightAswer) {
		super();
		this.quizString = quizString;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.optionE = optionE;
		this.rightAswer = rightAswer;
	}

	boolean answerControl(Answer answer) {
		return rightAswer == answer;
	}

}
