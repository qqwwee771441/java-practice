package com.fxmx.exam;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Iterator;
import java.util.ListIterator;

public class DataStructure {
	public static void main(String[] args) {
		System.out.println("Data Structure");
		
		List<Integer> list = Arrays.asList(4, 8, 7, 3);
		list = new ArrayList<>(list);
		list = new LinkedList<>(list);
		System.out.println(list);
		
		list.add(10); 
		System.out.println(list);
		list.set(list.size()-1, 100);
		System.out.println(list);
		list.remove(list.size()-1);
		System.out.println(list);
		
		/* for */
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		
		/* for-each (enhanced for) */
		for(Integer i : list) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		/* iterator */
		Iterator<Integer> itr = list.iterator();
		for(Integer i; itr.hasNext(); ) {
			i = itr.next();
			System.out.print(i + " ");
		}
		System.out.println();
		
		/* list iterator */
		ListIterator<Integer> litr = list.listIterator();
		for(Integer i; litr.hasNext(); ) {
			i = litr.next();
			System.out.print(i + " ");
			litr.add(i);
		}
		System.out.println();
		for(Integer i; litr.hasPrevious(); ) {
			i = litr.previous();
			System.out.print(i + " ");
			litr.set(i+1);
		}
		System.out.println();
		for(Integer i; litr.hasNext(); ) {
			i = litr.next();
			System.out.print(i + " ");
			litr.remove();
		}
		System.out.println();
		System.out.println(list);
	}
}
