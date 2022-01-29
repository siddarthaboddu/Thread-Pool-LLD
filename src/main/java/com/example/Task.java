package com.example;

public class Task{

	private String name;
	
	public Task(String name) {
		this.name = name;
	}
	
	public void run() {
		System.out.println(String.format("started execution of task: %s", getName()));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(String.format("finished execution of task: %s", getName()));
	}

	private Object getName() {
		return this.name;
	}
	
}
