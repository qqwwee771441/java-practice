package com.choi.craft;

import java.io.Serializable;
import java.util.Objects;

public class PersonalInfo implements Serializable {
	private String name;
	private String tel;
	private static final long serialVersionUID = 1L;
	
	public PersonalInfo(String n, String t) {
		name = n;
		tel = t;
	}
	@Override
	public String toString() {
		return "[Name: " + name + ", Tel: " + tel + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, tel);
	}
}
