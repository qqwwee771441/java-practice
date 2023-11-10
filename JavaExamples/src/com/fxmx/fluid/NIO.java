package com.fxmx.fluid;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class NIO {
	public static void main(String[] args) {
		/******************** PT ********************/
		Path path = Paths.get("C:\\Users\\Admin\\eclipse-workspace\\Examples\\data.dat");
		Path root = path.getRoot();
		Path parent = path.getParent();
		Path fileName = path.getFileName();
		System.out.println(path);
		System.out.println(root);
		System.out.println(parent);
		System.out.println(fileName);
		
		Path cur= Paths.get("");
		System.out.println(cur.isAbsolute()?cur:cur.toAbsolutePath());
		
		/******************** DF ********************/
		try {
			Path directories = Files.createDirectories(Paths.get("OuterDirectory\\InnerDirectory"));
			// absolute path if not present, relative path if present
			System.out.println(directories);
			Path directory = Files.createDirectory(Paths.get("SingleDirectory"));
			System.out.println(directory);
			Path file = Files.createFile(Paths.get("SingleDirectory\\just.dat"));
			System.out.println(file);
		}
		catch(FileAlreadyExistsException e) {
			System.out.println("Already Done");
			try {
				Path file = Files.createFile(Paths.get("SingleDirectory\\just.dat"));
				System.out.println(file);
			}
			catch(FileAlreadyExistsException _e) {
				System.out.println("Already Done");
			}
			catch(IOException _e) {
				e.printStackTrace();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		/******************** RW ********************/
		try {
			byte[] buf = new byte[] {0x13, 0x14, 0x15};
			Files.write(path, buf, 
					// StandardOpenOption.APPEND
					StandardOpenOption.CREATE,
					// StandardOpenOption.CREATE_NEW
					StandardOpenOption.TRUNCATE_EXISTING
					);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		try {
			byte[] buf = Files.readAllBytes(path);
			for(byte b : buf)
				System.out.print(b + "\t");
			System.out.println();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		/******************** RW ********************/
		try {
			List<String> list = Arrays.asList("simple", "sample", "function");
			list = new ArrayList<>(list);
			Files.write(Paths.get("text.txt"), list, 
					StandardOpenOption.CREATE, 
					StandardOpenOption.TRUNCATE_EXISTING);
			list = Files.readAllLines(Paths.get("text.txt"));
			System.out.println(list);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		/******************** CM ********************/
		try {
			Path copy = Paths.get("copy.dat");
			Files.copy(path, copy, 
					StandardCopyOption.REPLACE_EXISTING
					// StandardCopyOption.COPY_ATTRIBUTES
					);
			Files.move(copy, Paths.get("SingleDirectory\\copyAndMove.dat"), 
					StandardCopyOption.REPLACE_EXISTING);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
