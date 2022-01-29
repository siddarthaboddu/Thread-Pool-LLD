package com.example;

import java.util.concurrent.BlockingQueue;

public class ReusableThread<V extends Task> extends Thread {

	private boolean stop;
	private BlockingQueue<V> tasksQueue;
	
	public ReusableThread(BlockingQueue<V> tasksQueue, String threadName) {
		super(threadName);
		this.tasksQueue = tasksQueue;
		this.stop = false;
	}
		
	@Override
	public void run() {
		while(!stop) {
			V task = tasksQueue.poll();
			if(task != null)
				task.run();
		}
		
		printStopMessage();
	}
	
	private void printStopMessage() {
		System.out.println(String.format("ReusableThread %s stopped successfully", getName()));
	}

	public void doStop() {
		this.stop = true;
	}

}
