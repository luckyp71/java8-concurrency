package com.example.java8_concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example3 {

	public static void main (String[] args) {
		
		ExecutorService ex = Executors.newWorkStealingPool();
		
		// Example 1 (CompletableFuture with Void type).
		// runAsync() method is used when the method doesn't return any value so that we need use Void as our method return type.
		CompletableFuture<Void> cf1 = CompletableFuture.runAsync(()->{
			System.out.println("This is from thread "+Thread.currentThread().getName());
		});
		
		try {
			cf1.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		// Still same with the example above, but we add ExecutorService on our CompletableFuture.
		CompletableFuture<Void> cf2 = CompletableFuture.runAsync(()->{
			System.out.println("This is from thread "+Thread.currentThread().getName());
		},ex);
		
		try {
			cf2.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		// Example 2 (CompletableFuture which return particular value).
		// When we specify explicitly the return type of the method,
		// we must use supplyAsync() method on our CompletableFuture.
		CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(()->{
			return "This is from thread "+Thread.currentThread().getName();
		});
		
		try {
			System.out.println(cf3.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}                
}
