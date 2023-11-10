package com.advanced;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.lang.StringBuilder;
import java.util.Random;

public class Dictionary implements Serializable {
	public static final int DEFAULT_LEN = 500;
	private List<Word> wordList;
	private int numOfWords;
	private static final long serialVersionUID = 1L;
	
	public Dictionary(int len) {
		wordList = new ArrayList<>(len);
		numOfWords = 0;
	}
	public Dictionary() {
		this(DEFAULT_LEN);
	}
	
	public void add(Word w) {
		wordList.add(w);
		numOfWords++;
	}
	public void add(Word[] wordArr) {
		for(Word w : wordArr) {
			wordList.add(w);
			numOfWords++;
		}
	}
	public int getNumOfWords() { return numOfWords; }
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Word w : wordList)
			sb.append(w.toString() + "\n");
		return sb.toString();
	}
	public List<Word> getWordList() {
		return wordList;
	}
	
	public void sort() {
		Collections.sort(wordList);
	}
	public void sortRandom() {
		Random rand = new Random();
		int r1;
		int r2;
		Word temp;
		
		for(int i=0; i<numOfWords; i++) {
			r1 = rand.nextInt(numOfWords);
			r2 = rand.nextInt(numOfWords);;
			
			temp = wordList.get(r1);
			wordList.set(r1, wordList.get(r2));
			wordList.set(r2, temp);
		}
	}
}

class DictionaryMain {
	public static void main(String[] args) {
		Dictionary dic = new Dictionary();
		
		dic.add(new Word("initial 처음의, 최초의"));
		dic.add(new Word("necessarily 반드시"));
		dic.add(new Word("engrave (문자, 도안 등을) ~에 새기다"));
		
		System.out.println(dic);
	}
}
