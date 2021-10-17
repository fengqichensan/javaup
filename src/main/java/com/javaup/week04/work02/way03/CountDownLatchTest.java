package com.javaup.week04.work02.way03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.javaup.week04.work02.NotifyObject;

public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		NotifyObject notifyObject = new NotifyObject();
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		Thread subThread = new Thread(new SubThread(notifyObject, countDownLatch), "subThread");
		subThread.start();
		System.out.println(Thread.currentThread().getName() + " waiting " + subThread.getName() + "...");
		countDownLatch.await();
		System.out.println(Thread.currentThread().getName() + " running...");
		System.out.println("waitNotifyObject.workThreadName:" + notifyObject.getWorkThreadName());
	}

	static class SubThread implements Runnable {
		private NotifyObject notifyObject;
		private CountDownLatch countDownLatch;
		
		public SubThread (NotifyObject notifyObject, CountDownLatch countDownLatch) {
			this.notifyObject = notifyObject;
			this.countDownLatch = countDownLatch;
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
			countDownLatch.countDown();
			System.out.println(Thread.currentThread().getName() + " end...");
		}
		
	} 
}
