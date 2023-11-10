package com.game.renju;

import java.util.Arrays;

enum Stone {
	BLACK("¡Ü"), WHITE("¡Û"), EMPTY("¦«"), WALL("¡á");
	
	public String shape;
	
	private Stone(String s) {
		shape = s;
	}
	@Override
	public String toString() {
		return this.shape;
	}
}

public class Checkerboard {
	private Stone[][] board;
	private int numofstone;
	private int lines;
	
	public Checkerboard(int lines) {
		this.lines = lines>1 ? lines : 1;
		board = new Stone[lines+2][lines+2];
		for(Stone[] arr : board) {
			Arrays.fill(arr, Stone.EMPTY);
		}
		for(int i=0; i<lines+2; i++) {
			board[i][0] = Stone.WALL;
			board[i][lines+1] = Stone.WALL;
			board[0][i] = Stone.WALL;
			board[lines+1][i] = Stone.WALL;
		}
		numofstone = 0;
	}
	public int numOfStone() {
		return numofstone;
	}
	public void print() {
		for(int i=1; i<=lines; i++) {
			for(int j=1; j<=lines; j++) {
				System.out.print(board[i][j] + "¦¡");
			}
			System.out.println();
		}
	}
	public boolean put(int xpos, int ypos, Stone s) {
		if(xpos<1 || ypos<1 || xpos>lines || ypos>lines) return false;
		else if(board[xpos][ypos] == Stone.EMPTY) {
			board[xpos][ypos] = s;
			return true;
		}
		else return false;
	}
}

class CheckerboardMain {
	public static void main(String[] args) {
		Checkerboard board = new Checkerboard(15);
		board.put(8, 8, Stone.BLACK);
		board.print();
	}
}
