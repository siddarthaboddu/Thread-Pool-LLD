package com.example;

public class Main {
	
	private static void example() {
		ThreadPool<Task> threadPool = new ThreadPool<>(5, 100);
		
		for(int i=0;i<20;i++) {
			threadPool.execute(new Task(String.format("customTask-%d", i)));
		}

		threadPool.waitTillAllTaskFinished();
		System.out.println("All Tasks Finished");
		
	}
	
	public static void main(String[] args) {
		example();
	}
	
}
