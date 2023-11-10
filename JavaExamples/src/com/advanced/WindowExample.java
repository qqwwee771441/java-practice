package com.advanced;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.io.IOException;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JEditorPane;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WindowExample {

	private JFrame frmWindowExample;
	private JTable table;
	private JEditorPane textCenter;
	private JScrollPane scrollPane;
	private final Action action = new SwingAction();
	
	private Dictionary curDictionary = null;
	private String curFileName = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowExample window = new WindowExample();
					window.frmWindowExample.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowExample() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWindowExample = new JFrame();
		frmWindowExample.setTitle("Window Example");
		frmWindowExample.setBounds(100, 100, 900, 600);
		frmWindowExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWindowExample.addComponentListener(new ResizeListener());
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(Color.WHITE);
		frmWindowExample.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		
		JMenuItem openFileMenuItem = new JMenuItem("Open File...");
		openFileMenuItem.addActionListener(new ListenerExample());
		fileMenu.add(openFileMenuItem);
		
		JSeparator separator = new JSeparator();
		fileMenu.add(separator);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save");
				
				if(curFileName == null) {
					System.out.println("No");
				}
				else {
					System.out.println("Proceeding");
					
					StringBuilder sb = new StringBuilder(curFileName);
					sb.delete(sb.length()-3, sb.length());
					sb.append("dic");
					String saveFileRoute = sb.toString();
					
					try {
						Files.createFile(Paths.get(saveFileRoute));
						System.out.println(saveFileRoute);
						try(ObjectOutputStream oos = 
								new ObjectOutputStream(
										new FileOutputStream(saveFileRoute))) {
							oos.writeObject(curDictionary);
							System.out.println("Done");
						}
						catch(IOException ex) {
							ex.printStackTrace();
						}
					}
					catch(IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		fileMenu.add(saveMenuItem);
		
		JMenuItem saveAsMenuItem = new JMenuItem("Save As...");
		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save As Txt");
				
				if(curFileName == null) {
					System.out.println("No");
				}
				else {
					System.out.println("Proceeding");
					
					try(BufferedWriter bw =
							new BufferedWriter(
									new FileWriter(curFileName))) {
						bw.write(textCenter.getText());
						System.out.println("Done");
					}
					catch(IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		fileMenu.add(saveAsMenuItem);
		
		JSeparator separator_1 = new JSeparator();
		fileMenu.add(separator_1);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		JMenuItem undoMenuItem = new JMenuItem("Undo");
		editMenu.add(undoMenuItem);
		
		JMenuItem redoMenuItem = new JMenuItem("Redo");
		editMenu.add(redoMenuItem);
		
		JSeparator separator_2 = new JSeparator();
		editMenu.add(separator_2);
		
		JMenuItem cutMenuItem = new JMenuItem("Cut");
		editMenu.add(cutMenuItem);
		
		JMenuItem copyMenuItem = new JMenuItem("Copy");
		editMenu.add(copyMenuItem);
		
		JMenuItem pasteMenuItem = new JMenuItem("Paste");
		editMenu.add(pasteMenuItem);
		
		JMenuItem deleteMenuItem = new JMenuItem("Delete");
		editMenu.add(deleteMenuItem);
		
		JSeparator separator_3 = new JSeparator();
		editMenu.add(separator_3);
		
		JMenuItem findMenuItem = new JMenuItem("Find/Replace...");
		editMenu.add(findMenuItem);
		
		JMenuItem selectAllMenuItem = new JMenuItem("Selecet All");
		editMenu.add(selectAllMenuItem);
		
		JMenu practiceMenu = new JMenu("Practice");
		menuBar.add(practiceMenu);
		
		JMenuItem examMenuItem = new JMenuItem("Exam");
		examMenuItem.addActionListener(action);
		practiceMenu.add(examMenuItem);
		
		JMenu windowMenu = new JMenu("Window");
		menuBar.add(windowMenu);
		
		JMenuItem appearanceMenuItem = new JMenuItem("Appearance");
		windowMenu.add(appearanceMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem helpMenuItem = new JMenuItem("Help");
		helpMenu.add(helpMenuItem);
		
		JMenuItem feedbackMenuItem = new JMenuItem("Send Feedback");
		helpMenu.add(feedbackMenuItem);
		
		JSeparator separator_4 = new JSeparator();
		helpMenu.add(separator_4);
		
		JMenuItem versionMenuItem = new JMenuItem("Version Information");
		helpMenu.add(versionMenuItem);
		frmWindowExample.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel = new JPanel();
		frmWindowExample.getContentPane().add(leftPanel, BorderLayout.WEST);
		
		/******************** ******************** ********************/
		String[] header = {"ENGLISH", "KOREAN"};
		String[][] contents = { };
		DefaultTableModel dtm = new DefaultTableModel(contents, header);
		table = new JTable(dtm);
		table.setPreferredSize(new Dimension(400, 500));
		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(400, 500));
		leftPanel.add(sp);
		
		JPanel bottomPanel = new JPanel();
		frmWindowExample.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		Path curPath = Paths.get("");
		JLabel etcInfoLabel = new JLabel(curPath.toAbsolutePath().toString());
		bottomPanel.add(etcInfoLabel);
		
		JPanel eastPanel = new JPanel();
		frmWindowExample.getContentPane().add(eastPanel, BorderLayout.EAST);
		
		textCenter = new JEditorPane();
		textCenter.setPreferredSize(new Dimension(450, 500));
		scrollPane = new JScrollPane(textCenter);
		scrollPane.setPreferredSize(new Dimension(450, 500));
		eastPanel.add(scrollPane);
	}
	
	class ListenerExample implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			
			JFileChooser fileDialog = new JFileChooser();
			
			fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileFilter[] allFilters = fileDialog.getChoosableFileFilters();
			for(FileFilter f : allFilters)
				fileDialog.removeChoosableFileFilter(f);
			FileFilter filter = new FileNameExtensionFilter("TXT file", "txt");
			fileDialog.setFileFilter(filter);
			
			File wallpaper = new File("C:\\Users\\Admin\\Desktop\\toeic\\");
			if(wallpaper.exists()) {
				fileDialog.setCurrentDirectory(wallpaper);
			}
			
			int returnVal = fileDialog.showOpenDialog(null);
			if(returnVal == 0) {
				String selectedFileStr = fileDialog.getSelectedFile().getAbsolutePath();
				System.out.println(selectedFileStr);
				
				try(BufferedReader br =
						new BufferedReader(
								new FileReader(selectedFileStr))) {
					
					Dictionary dic = new Dictionary();
					while(true) {
						String str = br.readLine();
						if(str == null)
							break;
						dic.add(new Word(str));
					}
					System.out.println(dic);
					
					textCenter.setText(dic.toString());
					frmWindowExample.setTitle("Window Example - " + selectedFileStr);
					
					DefaultTableModel dtm = (DefaultTableModel)(table.getModel());
					
					List<Word> list = dic.getWordList();
					for(int i=0; i<dic.getNumOfWords(); i++) {
						dtm.insertRow(i, new Object[] {list.get(i).getEnglish(), list.get(i).getKorean()});
					}
					table.setPreferredSize(new Dimension(400, dic.getNumOfWords()*16));
					table.updateUI();
					
					curDictionary = dic;
					curFileName = selectedFileStr;
				}
				catch(IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
	
	class ResizeListener extends ComponentAdapter {
		public void componentResized(ComponentEvent e) {
			System.out.println("resizing");
			
			int getx = e.getComponent().getX();
			int gety = e.getComponent().getY();
			int getw = e.getComponent().getWidth();
			int geth = e.getComponent().getHeight();
			
			System.out.println(getx + " " + gety);
			System.out.println(getw + " " + geth);
		}
	}
	
	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Exam");
			
			if(curFileName == null) {
				System.out.println("No");
			}
			else {
				StringBuilder sb = new StringBuilder(curFileName);
				sb.delete(sb.length()-3, sb.length());
				sb.append("dic");
				
				String[] para = { sb.toString() };
				WordTest.main(para);
			}
		}
	}
}
