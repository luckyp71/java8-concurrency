package com.example.java8_concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Example2 {

	public static void main(String[] args) {

		// Example 1 (SheculedExecutorService with ScheduleWithFixedDelay)
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> {
		   System.out.println("Scheduling: " + System.nanoTime());
		};

		ScheduledFuture<?> future = executor.scheduleWithFixedDelay(task, 0, 5, TimeUnit.SECONDS);
		
		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

}
