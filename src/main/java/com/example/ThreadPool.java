package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool<V extends Task> {

	private int threadCount;
	private int queueCapacity;

	private BlockingQueue<V> tasksQueue;
	private List<ReusableThread<V>> threadList;
	private boolean stopAll;

	public ThreadPool(int threadCount, int queueCapacity) {
		this.threadCount = threadCount;
		this.queueCapacity = queueCapacity;
		this.stopAll = false;
		
		this.tasksQueue = new ArrayBlockingQueue<>(this.queueCapacity);
		this.threadList = new ArrayList<>();

		for (int i = 0; i < this.threadCount; i++) {
			threadList.add(new ReusableThread<V>(tasksQueue, String.format("reusableThread-%d", i)));
		}
		
		for(ReusableThread<V> thread : threadList) {
			thread.start();
		}
		
	}
	
	
	public void execute(V task) {
		try {
			this.tasksQueue.put(task);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		if(this.stopAll) {
			throw new IllegalStateException();
		}
		this.stopAll = true;
		for(ReusableThread<V> thread : threadList) {
			thread.doStop();
		}
	}
	
	public boolean isStopped() {
		return this.stopAll;
	}
	
	public void waitTillAllTaskFinished() {
		while(this.tasksQueue.size() > 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
