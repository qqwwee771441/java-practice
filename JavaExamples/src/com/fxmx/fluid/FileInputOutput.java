package com.fxmx.fluid;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;

import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.io.IOException;

import com.choi.craft.User;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

// import java.util.Scanner;

public class FileInputOutput {
	public static void main(String[] args) {
		try(OutputStream out = new FileOutputStream("data.dat")) {
			out.write(10);
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		try(InputStream in = new FileInputStream("data.dat")) {
			int data = in.read();
			System.out.println(data);
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		
		/*
		Scanner kb = new Scanner(System.in);
		System.out.print("orgn: ");
		String org = kb.nextLine();
		System.out.print("copy: ");
		String cpy = kb.nextLine();
		kb.close();
		*/
		/*
		try(InputStream in = new FileInputStream(org) ;
				OutputStream out = new FileOutputStream(cpy)) {
			
			int data;
			while(true) {
				data = in.read();
				if(data == -1)
					break;
				out.write(data);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		*/
		try(InputStream in = new FileInputStream("text.txt") ;
				OutputStream out = new FileOutputStream("copy.txt")) {
			byte[] buf = new byte[1024];
			while(true) {
				int len = in.read(buf);
				if(len == -1)
					break;
				out.write(buf, 0, len);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		try(DataOutputStream out =
				new DataOutputStream(
						new BufferedOutputStream(
								new FileOutputStream("data.dat")))) {
			out.writeInt(1000);
			out.writeDouble(Math.PI);
			out.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		try(DataInputStream in =
				new DataInputStream(
						new BufferedInputStream(
								new FileInputStream("data.dat")))) {
			int i = in.readInt();
			double d = in.readDouble();
			System.out.println(i + " " + d);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		/******************** RW ********************/
		try(Reader in = new FileReader("text.txt")) {
			int ch;
			while(true) {
				ch = in.read();
				if(ch == -1)
					break;
				System.out.print((char)ch);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		try(Writer out = new FileWriter("text.txt")) {
			for(int ch=(int)'A'; ch<=(int)'Z'; ch++)
				out.write(ch);
			System.out.println();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		try(BufferedWriter out = new BufferedWriter(new FileWriter("text.txt"))) {
			out.write("Simple String");
			out.newLine();
			String str = "Now Create Line";
			out.write(str, 0, str.length());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		try(BufferedReader in = new BufferedReader(new FileReader("text.txt"))) {
			String str;
			while(true) {
				str = in.readLine();
				if(str == null)
					break;
				System.out.println(str);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		/******************** O ********************/
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("object.bin"))) {
			User user = new User("qqwwee41", "aassdd41", "qqwwee41", "ChoiKwangJin", "010-2789-4873");
			user.useService("Main Page");
			System.out.println(user);
			out.writeObject(user);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("object.bin"))) {
			User user = (User)in.readObject();
			System.out.println(user);
		}
		catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		/******************** NIO2 ********************/
		Path path = Paths.get("data.dat");
		try(DataOutputStream out = 
				new DataOutputStream(
						Files.newOutputStream(path))) {
			out.writeChar((int)'A');
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		try(DataInputStream in =
				new DataInputStream(
						Files.newInputStream(path))) {
			char ch = in.readChar();
			System.out.println(ch);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		path = Paths.get("text.txt");
		try(BufferedWriter out = Files.newBufferedWriter(path)) {
			out.write("File New Buffered Static");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		try(BufferedReader in = Files.newBufferedReader(path)) {
			System.out.println(in.readLine());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("End");
	}
}
