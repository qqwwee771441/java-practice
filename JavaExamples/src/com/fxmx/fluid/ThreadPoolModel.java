package com.fxmx.fluid;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

import java.lang.Thread;
import java.lang.Runnable;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import java.lang.InterruptedException;
import java.util.concurrent.ExecutionException;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Collections;

public class ThreadPoolModel {
	public static List<Integer> list;
	public static ReentrantLock rl = new ReentrantLock();
	static {
		list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		list = new ArrayList<>(list);
		list = Collections.synchronizedList(list);
	}
	
	public static void main(String[] args) {
		System.out.println("<Thread Pool Model>");
		
		// ExecutorService es = Executors.newSingleThreadExecutor();
		ExecutorService es = Executors.newFixedThreadPool(4);
		// ExecutorService es = Executors.newCachedThreadPool();
		
		/******************** THREAD 1 ********************/
		es.submit(()->{
			System.out.println(Thread.currentThread().getName());
		});
		/******************** THREAD 2 ********************/
		Callable<Integer> c = ()->{
			System.out.println(Thread.currentThread().getName());
			int sum = 0;
			for(int i=1; i<=10; i++)
				sum += i;
			return sum;
		};
		Future<Integer> f = es.submit(c);
		try {
			System.out.println(f.get());
		}
		catch(InterruptedException|ExecutionException e) {
			e.printStackTrace();
		}
		/******************** THREAD 3 ********************/
		Runnable r = ()->{
			rl.lock();
			ListIterator<Integer> i = list.listIterator();
			while(i.hasNext())
				i.set(i.next()+1);
			rl.unlock();
		};
		es.submit(r);
		es.submit(r);
		es.submit(r);
		es.submit(r);
		
		/******************** SHUTDOWN ********************/
		es.shutdown();
		try {
			es.awaitTermination(1000, TimeUnit.SECONDS);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(list);
		System.out.println("END " + Thread.currentThread().getName());
	}
}
