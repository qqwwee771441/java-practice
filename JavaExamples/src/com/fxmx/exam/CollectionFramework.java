package com.fxmx.exam;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

import java.util.Iterator;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.PriorityQueue;

public class CollectionFramework {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("choi", "name", "card");
		list = new ArrayList<>(list);
		list = new LinkedList<>(list);
		list.forEach(System.out::println);
		
		Set<Integer> set = new HashSet<>();
		set.add(100);
		set.add(200);
		set = new TreeSet<>(set);
		set.forEach(System.out::println);
		
		Map<Integer, String> map = new HashMap<>();
		map.put(10, "ten");
		map.put(5, "five");
		map = new TreeMap<>(map);
		for(Iterator<Integer> itr = map.keySet().iterator(); itr.hasNext();) {
			System.out.println( map.get(itr.next()) );
		}
		
		Deque<String> stack = new ArrayDeque<>();
		stack.addFirst("first");
		stack.addFirst("second");
		stack = new LinkedList<>(stack);
		stack.forEach(System.out::println);
		
		Queue<String> queue = new PriorityQueue<>();
		queue.add("first");
		queue.add("second");
		queue = new LinkedList<>(queue);
		queue.forEach(System.out::println);
	}
}
