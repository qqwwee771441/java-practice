package com.advanced;

import java.io.Serializable;

import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.util.List;
import java.util.LinkedList;

public class Word implements Serializable, Comparable<Word> {
	private String english;
	private String korean;
	private static final long serialVersionUID = 1L;
	
	public Word(String line) {
		StringTokenizer st = new StringTokenizer(line, ",()\s", true);
		String token;
		
		List<String> list = new LinkedList<>();
		String str;
		while(st.hasMoreTokens()) {
			token = st.nextToken();	
			str = token;
			if(token.equals("(")) {
				while(st.hasMoreTokens()) {
					token = st.nextToken();
					str += token;
					if(token.equals(")"))
						break;
				}
			}
			str = str.strip();
			if(str.equals(""))
				continue;
			list.add(str);
		}
		// System.out.println(list);
		
		StringBuilder eng = new StringBuilder();
		StringBuilder kor = new StringBuilder();
		
		StringBuilder cur = eng;
		for(String s : list) {
			if(s.matches(".*[¤¡-¤¾¤¿-¤Ó°¡-ÆR]+.*"))
				cur = kor;
			if(s.equals(","))
				cur.deleteCharAt(cur.length()-1);
			cur.append(s + " ");
		}
		english = eng.toString().stripTrailing();
		korean = kor.toString().stripTrailing();
	}
	public Word(String e, String k) {
		english = e;
		korean = k;
	}
	@Override
	// public String toString() { return "[ENGLISH] " + english + "\t" + "[KOREAN] " + korean; }
	public String toString() { return english + " " + korean; }
	public String getEnglish() { return english; }
	public String getKorean() { return korean; }
	@Override
	public int compareTo(Word w) {
		return english.compareTo(w.english);
	}
}

class WordMain {
	public static void main(String[] args) {
		Word word = new Word("abc(= asd), hgd (¾È³ç) °¡³ª´Ù, ÇÏ¼¼¿ä");
		System.out.println(word.getEnglish());
		System.out.println(word.getKorean());
	}
}
