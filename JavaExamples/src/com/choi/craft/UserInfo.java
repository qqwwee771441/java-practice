package com.choi.craft;

import java.io.Serializable;
import java.util.Objects;

public class UserInfo implements Serializable {
	String nickname;
	PersonalInfo pinfo;
	private static final long serialVersionUID = 1L;
	
	public UserInfo(String n, PersonalInfo p) {
		nickname = n;
		pinfo = p;
	}
	public UserInfo(String ni, String na, String t) {
		nickname = ni;
		pinfo = new PersonalInfo(na, t);
	}
	@Override
	public String toString() {
		return nickname + " " + pinfo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nickname, pinfo);
	}
}
