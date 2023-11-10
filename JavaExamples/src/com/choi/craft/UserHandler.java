package com.choi.craft;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

import java.util.Comparator;

import java.util.Scanner;

public class UserHandler {
	private Set<User> userset;
	private int numofuser;
	
	public static UserHandler ofHashSet() {
		return new UserHandler();
	}
	public static UserHandler ofTreeSet() {
		UserHandler uh = new UserHandler();
		uh.userset = new TreeSet<>();
		return uh;
	}
	public static UserHandler ofTreeSet(Comparator<User> comparator) {
		UserHandler uh = new UserHandler();
		uh.userset = new TreeSet<>(comparator);
		return uh;
	}
	public UserHandler() {
		userset = new HashSet<>();
		numofuser = 0;
	}
	public int numOfUser() {
		return numofuser;
	}
	public void printAllUsers() {
		userset.forEach(System.out::println);
	}
	public boolean offer(User newuser) {
		for(User u : userset)
			if(u == newuser)
				return false;
		userset.add(newuser);
		return true;
	}
	public User peek(String id, String pw) {
		User cmp = new User(id, pw, null);
		for(User u : userset)
			if(u.equals(cmp))
				return u;
		return null;
	}
}

class UserComparator implements Comparator<User> {
	@Override
	public int compare(User u1, User u2) {
		return -u1.compareTo(u2);
	}
}

class UserHandlerMain {
	public static void main(String[] args) {
		// UserHandler uh = new UserHandler();
		// UserHandler uh = UserHandler.ofTreeSet( (u1, u2)->-u1.compareTo(u2) );
		// UserHandler uh = UserHandler.ofTreeSet( new UserComparator() );
		UserHandler uh = UserHandler.ofTreeSet( new Comparator<User>() { @Override
			public int compare(User u1, User u2) {
			return -u1.compareTo(u2);
		} } );
		
		PersonalInfo pi = new PersonalInfo("ChoiKwangJin", "010-2789-4873");
		UserInfo ui = new UserInfo("qqwwee41", pi);
		User newuser = new User("qqwwee41", "aassdd41", ui);
		uh.offer(newuser);
		
		pi = new PersonalInfo("ChoiKwangJin", "010-2789-4873");
		ui = new UserInfo("qqwwee4873", pi);
		newuser = new User("qqwwee4873", "aassdd4873", ui);
		uh.offer(newuser);
		pi = new PersonalInfo("ChoiKwangJin", "010-2789-4873");
		ui = new UserInfo("rhkdwls", pi);
		newuser = new User("rhkdwls4873", "3144873", ui);
		uh.offer(newuser);
		
		String id = null;
		String pw = null;
		try( Scanner kb = new Scanner(System.in) ) {
			System.out.print("ID> ");
			id = kb.nextLine();
			System.out.print("PASSWORD> ");
			pw = kb.nextLine();
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
		User user = uh.peek(id, pw);
		
		System.out.println(user);
		
		System.out.println();
		// uh.printAllUsers();
	}
}
