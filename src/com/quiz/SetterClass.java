package com.quiz;

public class SetterClass extends QuizController{	
	
public static void main(String[] args) {
	SetterClass obj = new SetterClass();
	obj.setQuiz(1);
}
	
	@Override
	public void setQuiz(int quizNo) {
		
		System.out.println("quiz: " + questions[quizNo].quizString);
		
//		label.setText(" " + questions[quizNo].quizString);
//		buttonA.setText(charcterControl(questions[quizNo].optionA));
//		buttonB.setText(charcterControl(questions[quizNo].optionB));
//		buttonC.setText(charcterControl(questions[quizNo].optionC));
//		buttonD.setText(charcterControl(questions[quizNo].optionD));
//		buttonE.setText(charcterControl(questions[quizNo].optionE));
		
	}


}
