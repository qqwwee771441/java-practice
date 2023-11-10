package com.fxmx.fluid;

import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.io.IOException;

public class ChannelExample {
	public static void main(String[] args) {
		System.out.println("Channel Example");
		
		Path src = Paths.get("data.dat");
		Path dst = Paths.get("SingleDirectory\\data.dat");
		
		ByteBuffer buf = ByteBuffer.allocate(1024); // 0(R)
		// ByteBuffer buf = ByteBuffer.allocateDirect(1024);
		
		/******************** COPY BASED NIO ********************/
		try(FileChannel ifc = FileChannel.open(src, StandardOpenOption.READ);
				FileChannel ofc = FileChannel.open(
						dst, 
						StandardOpenOption.WRITE,
						StandardOpenOption.CREATE)) {
			int num;
			while(true) {
				num = ifc.read(buf); // 0(R) -> num(R)
				if(num == -1)
					break;
				buf.flip(); // num(R) -> 0(W)
				ofc.write(buf); // 0(W) -> num(W)
				buf.clear(); // num(W) -> 0(R)
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		/******************** FILE RANDOM ACCESS ********************/
		buf.putInt(100);
		buf.putDouble(Math.E);
		try(FileChannel fc = FileChannel.open(src, 
				StandardOpenOption.CREATE,
				StandardOpenOption.READ,
				StandardOpenOption.WRITE)) {
			buf.flip();
			fc.write(buf); // wb
			buf.clear();
			fc.position(0);
			fc.read(buf); // rb
			buf.flip();
			System.out.println(buf.getInt());
			buf.position(Integer.BYTES);
			System.out.println(buf.getDouble());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("End");
	}
}
