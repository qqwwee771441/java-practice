package com.fxmx.exam;

public class GenericExample {
	public static void main(String[] args) {
		System.out.println("Generic Example");
		
		Box<Integer> box = BoxFactory.makeBox(100);
		System.out.println(box);
		BoxHandler.setContents(box, 200);
		System.out.println( BoxHandler.getContents(box) );
	}
}

class Box<T extends Number> {
	private T contents;
	public Box(T conts) {
		contents = conts;
	}
	public void set(T conts) {
		contents = conts;
	}
	public T get() {
		return contents;
	}
	public String toString() {
		return "[" + contents + "]";
	}
	public int toIntValue() {
		return contents.intValue();
	}
}

class BoxFactory {
	public static <T extends Number> Box<T> makeBox(T conts) {
		Box<T> box = new Box<>(conts);
		return box;
	}
}

class BoxHandler {
	public static <T // extends Number
	> void setContents(Box<? super T> box, T conts) {
		box.set(conts);
	}
	public static <T extends Number> T getContents(Box<? extends T> box) {
		return box.get();
	}
}
