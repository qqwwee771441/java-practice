package com.choi.craft;

public class Unit implements Cloneable {
	private String name;
	public Unit(String name) {
		this.name = name;
	}
	@Override
	public Unit clone() throws CloneNotSupportedException {
		return (Unit)super.clone();
	}
	@Override
	public String toString() {
		return name;
	}
}

class Main {
	public static void main(String[] args) throws CloneNotSupportedException {
		Unit unit1 = new Unit("first");
		Unit unit2 = unit1.clone();
		System.out.println(unit2);
	}
}
