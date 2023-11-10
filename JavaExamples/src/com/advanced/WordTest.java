package com.advanced;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class WordTest {

	private JFrame frame;
	private String curFileRoute;
	private Dictionary curDictionary;
	private int count = 0;
	
	private JLabel englishLabel;
	private JLabel solutionLabel;
	
	private static String ARG;
	private JTextField koreanText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ARG = args[0];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordTest window = new WordTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WordTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/******************** LOAD ********************/
		curFileRoute = ARG;
		try(ObjectInputStream ois =
				new ObjectInputStream(
						new FileInputStream(curFileRoute))) {
			curDictionary = (Dictionary)ois.readObject();
		}
		catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		// curDictionary.sort();
		curDictionary.sortRandom();
		System.out.println(curDictionary);
		
		JPanel northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		
		englishLabel = new JLabel(curDictionary.getWordList().get(0).getEnglish());
		englishLabel.setFont(new Font("±¼¸²", Font.PLAIN, 64));
		northPanel.add(englishLabel);
		
		/******************** BOTTOM ********************/
		JPanel southPanel = new JPanel();
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		solutionLabel = new JLabel("");
		solutionLabel.setForeground(Color.GRAY);
		solutionLabel.setFont(new Font("±¼¸²", Font.PLAIN, 48));
		southPanel.add(solutionLabel);
		
		/******************** CENTER ********************/
		JPanel centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		koreanText = new JTextField();
		koreanText.addActionListener(new ActionListener() { // KoreanText Enter to Action
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				System.out.println(command);
				
				if(count+2 > curDictionary.getNumOfWords()) {
					if(count == curDictionary.getNumOfWords()) {
						solutionLabel.setText("");
						englishLabel.setText("");
						koreanText.setText("");
						System.out.println("No More Word Data...");
						return;
					}
					Word curWord = curDictionary.getWordList().get(count++);
					solutionLabel.setText(curWord.getKorean());
					englishLabel.setText("");
					koreanText.setText("");
					return;
				}
				
				Word curWord = curDictionary.getWordList().get(count++);
				solutionLabel.setText(curWord.getKorean());
				englishLabel.setText(curDictionary.getWordList().get(count).getEnglish());
				koreanText.setText("");
			}
		});
		koreanText.setFont(new Font("±¼¸²", Font.PLAIN, 32));
		centerPanel.add(koreanText);
		koreanText.setColumns(10);
		
	}
}
