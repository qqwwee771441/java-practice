package com.orange;

public class Friend {
	String name;
	Company cmp;
	public Friend(String n, Company c) {
		name = n;
		cmp = c;
	}
	public String getName() { return name; }
	public Company getCmp() { return cmp; }
}

class Company {
	String cName;
	ContInfo cInfo;
	public Company(String cn, ContInfo ci) {
		cName = cn;
		cInfo = ci;
	}
	public String getCName() { return cName; }
	public ContInfo getCInfo() { return cInfo; }
}

class ContInfo {
	String phone;
	String adrs;
	public ContInfo(String ph, String ad) {
		phone = ph;
		adrs = ad;
	}
	public String getPhone() { return phone; }
	public String getAdrs() { return adrs; }
}
