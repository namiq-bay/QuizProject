package com.quiz;

public class executorTest implements Runnable {

	public static void main(String[] args) {
		Thread t1 = new Thread(new executorTest());
		t1.start();
	}

	public void run() {

		System.out.println("MyClass running");

	}

}
