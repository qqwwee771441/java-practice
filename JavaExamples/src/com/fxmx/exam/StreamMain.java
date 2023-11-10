package com.fxmx.exam;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.util.stream.Stream;
import java.util.stream.IntStream;

public class StreamMain {
	public static void main(String[] args) {
		int result = Arrays.stream(new int[] {1, 2, 3, 4, 5})
						   .filter(n -> n%2==1)
						   .sum();
		System.out.println(result);
		Arrays.stream(new String[] {"A", "B", "C", "D", "E"})
			  .forEach(s -> System.out.print(s + " "));
		System.out.println();
		
		List<String> list = Arrays.asList("simple", "some", "any", "quick", "if");
		list = new ArrayList<>(list);
		list.stream()
			.mapToInt(s -> s.length())
			.sorted()
			.forEach(i -> System.out.print(i + " "));
		System.out.println();
		String str = list.parallelStream()
						 .peek(s -> System.out.print(s + " "))
						 .reduce("", (s1, s2) -> s1.length()>s2.length()?s1:s2);
		System.out.println("\n" + str);
		
		Stream.of("any_time", "more_often_than_not")
			  .flatMap(s -> Stream.of(s.split("_")) )
			  .forEach(s -> System.out.print(s + " "));
		System.out.println();
		list = Stream.concat(list.stream(), Stream.of("time", "more"))
					 .filter(s -> s.length()<5)
					 //.parallel()
					 .collect(ArrayList<String>::new, (l, s)->l.add(s), (l1, l2)->l1.addAll(l2));
		System.out.println(list);
		
		IntStream.of(1, 2, 3, 4, 5).sum();
		IntStream.of(1, 2, 3, 4, 5).count();
		IntStream.of(1, 2, 3, 4, 5).max().ifPresent(System.out::println);
		IntStream.of(1, 2, 3, 4, 5).min().ifPresent(System.out::println);
		IntStream.of(1, 2, 3, 4, 5).average().ifPresent(System.out::println);
		
		IntStream.of(1, 2, 3, 4, 5).allMatch(n -> n%2==0);
		IntStream.of(1, 2, 3, 4, 5).anyMatch(n -> n%2==0);
		IntStream.of(1, 2, 3, 4, 5).noneMatch(n -> n%2==0);
	}
}
