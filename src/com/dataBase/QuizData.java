package com.dataBase;


public class QuizData {

	public String id;
	public String userID;
	public String userName;
	public String quizName;
	public String trueCount;
	public String falseCount;

	public QuizData(String id, String userID, String userName, String quizName, String trueCount, String falseCount) {
		super();
		this.id = id;
		this.userID = userID;
		this.userName = userName;
		this.quizName = quizName;
		this.trueCount = trueCount;
		this.falseCount = falseCount;
	}
}
