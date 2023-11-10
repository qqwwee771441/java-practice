package com.fxmx.exam;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import com.choi.craft.User;
import java.util.function.Function;

/*
 * Predicate<T> filter boolean test(T t)
 * Supplier<T> T get()
 * Consumer<T> void accept(T t)
 * Function<T, R> action R apply(T t)
 * */

public class Lambda {
	public static void main(String[] args) {
		System.out.println("Lambda Main");
		
		List<User> userlist = Arrays.asList(
				new User("qqwwee41", "aassdd41", "qqww41", "ChoiKwangJin", "10-2789-4873"),
				new User("qqwwee4873", "aassdd4873", "qqwwee771441", "CKJ", "010-2789-4873"),
				new User("rhkdwls4873", "3144873", "rhkdwls", "RhkdWls", "010-2222-2222"));
		userlist = new LinkedList<>(userlist);
		
		System.out.println("print");
		Printer.print(userlist, e->"{ " + e.toString() + " }");
		
		userlist.removeIf( e->e.ID().length()==8 );
		System.out.println("print2");
		Printer.print2(userlist, e->"{ " + e.toString() + " }");
	}
}

@FunctionalInterface
interface Format<T> {
	String convert(T t);
}

class Printer {
	public static <T> void print(List<? extends T> list, Format<? super T> format) {
		list.forEach( e -> System.out.println( format.convert(e) ) );
	}
	public static <T> void print2(List<? extends T> list, Function<T, String> action) {
		list.forEach( e -> System.out.println( action.apply(e) ) );
	}
}
