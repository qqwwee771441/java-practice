package com.choi.craft;

import java.util.Objects;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable {
	String id;
	String password;
	UserInfo uinfo;
	transient Service current = new Service();
	private static final long serialVersionUID = 1L;
	
	public User(String i, String p, UserInfo u) {
		id = i;
		password = p;
		uinfo = u;
	}
	public User(String i, String p, String ni, String na, String t) {
		id = i;
		password = p;
		uinfo = new UserInfo(ni, na, t);
	}
	@Override
	public String toString() {
		return "(" + id + ")" + uinfo + " " + current;
	}
	@Override
	public boolean equals(Object obj) {
		User u = (User)obj;
		return id.equals(u.id) && password.equals(u.password);
	}
	@Override
	public int compareTo(User cmp) {
		return id.compareTo(cmp.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, password, uinfo);
	}
	public String ID() {
		return id;
	}
	public void useService(String s) {
		current.setUse(s);
	}
}

class Service {
	private String use;
	public Service() {
		use = null;
	}
	@Override
	public String toString() {
		return use;
	}
	@Override
	public int hashCode() {
		return Objects.hash(use);
	}
	public void setUse(String s) {
		use = s;
	}
}
