package com.javaup.week04.work02.way01;

import java.util.concurrent.TimeUnit;

import com.javaup.week04.work02.NotifyObject;

/**
 * 利用wait/notify（notifyAll）
 *
 */
public class WaitNotify {

	static NotifyObject notifyObject = new NotifyObject();
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread subThread = new Thread(new SubThread(), "subThread");
		subThread.start();
		synchronized (notifyObject) {
			System.out.println(Thread.currentThread().getName() + " wait...");
			notifyObject.wait();
		}
		System.out.println(Thread.currentThread().getName() + " running...");
		System.out.println("waitNotifyObject.workThreadName:" + notifyObject.getWorkThreadName());
	}

	static class SubThread implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " running...");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (notifyObject) {
				notifyObject.setWorkThreadName(Thread.currentThread().getName());
				notifyObject.notify();
			}
			System.out.println(Thread.currentThread().getName() + " end...");
		}
		
	} 
}
