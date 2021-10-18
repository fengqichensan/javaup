package com.javaup.week04.work02.way04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class FeatureTaskTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<String> future = executorService.submit(new SubThread());
		System.out.println(Thread.currentThread().getName() + " waiting...");
		String reuslt = future.get();
		System.out.println(Thread.currentThread().getName() + " running...");
		System.out.println("reuslt:" + reuslt);
		executorService.shutdown();
	}

	static class SubThread implements Callable<String> {
		
		@Override
		public String call() throws Exception {
			System.out.println(Thread.currentThread().getName() + " running...");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " end...");
			return Thread.currentThread().getName();
		}
		
	}
}
