package com.fxmx.fluid;

import java.lang.Thread;
import java.lang.InterruptedException;

public class ThreadExample {
	public static Counter counter = new Counter();
	
	public static void main(String[] args) {
		System.out.println("<Thread Example>");
		printStart();
		
		/******************** 0 ********************/
		Thread thread = new Thread(()->{
			printStart();
			printEnd();
		});
		thread.start();
		
		/******************** 1 ********************/
		Task task = new Task();
		task.start();
		try {
			task.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		/******************** 2 3 ********************/
		Thread t1 = new Thread(()->{
			printStart();
			for(int i=0; i<10000; i++)
				counter.increment();
			printEnd();
		});
		Thread t2 = new Thread(()->{
			printStart();
			for(int i=0; i<10000; i++)
				counter.decrement();
			printEnd();
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(counter.count);
		
		/******************** 4 5 ********************/
		t1 = new Thread(()->{
			printStart();
			for(int i=0; i<10000; i++)
				counter.increase(2);
			printEnd();
		});
		t2 = new Thread(()->{
			printStart();
			for(int i=0; i<10000; i++)
				counter.decrease(2);
			printEnd();
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(counter.count);
		
		printEnd();
	}
	public static void printStart() {
		System.out.println(Thread.currentThread().getName() + "\tSTART");
	}
	public static void printEnd() {
		System.out.println(Thread.currentThread().getName() + "\tEND");
	}
}

class Task extends Thread {
	@Override
	public void run() {
		ThreadExample.printStart();
		try {
			Thread.sleep(1000L);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		ThreadExample.printEnd();
	}
}

class Counter {
	int count = 0;
	synchronized public void increment() {
		count++;
	}
	synchronized public void decrement() {
		count--;
	}
	public void increase(int num) {
		synchronized(this) {
			count += num;
		}
	}
	public void decrease(int num) {
		synchronized(this) {
			count -= num;
		}
	}
}
