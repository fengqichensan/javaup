package com.javaup.week04.work02.way02;

import java.util.concurrent.TimeUnit;

import com.javaup.week04.work02.NotifyObject;

public class Join {

	public static void main(String[] args) throws InterruptedException {
		NotifyObject notifyObject = new NotifyObject();
		
		Thread subThread = new Thread(new SubThread(notifyObject), "subThread");
		subThread.start();
		
		System.out.println(Thread.currentThread().getName() + " waiting " + subThread.getName() + "...");
		subThread.join();
		System.out.println(Thread.currentThread().getName() + " running...");
		System.out.println("waitNotifyObject.workThreadName:" + notifyObject.getWorkThreadName());
	}

	static class SubThread implements Runnable {
		private NotifyObject notifyObject;
		public SubThread (NotifyObject notifyObject) {
			this.notifyObject = notifyObject;
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " running...");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			notifyObject.setWorkThreadName(Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getName() + " end...");
		}
		
	} 
}
