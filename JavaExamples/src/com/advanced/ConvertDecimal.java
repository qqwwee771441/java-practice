package com.advanced;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

import java.lang.Integer;

public class ConvertDecimal {

	private JFrame frame;
	private JTextField txtDone;
	private final Action action = new SwingAction();
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConvertDecimal window = new ConvertDecimal();
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
	public ConvertDecimal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtDone = new JTextField();
		txtDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = txtDone.getText();
				String[] tokens = str.split(" ");
				str = String.format("0x%02x%02x%02x", 
						Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
				lblNewLabel.setText(str);
			}
		});
		txtDone.setText("000 000 000");
		frame.getContentPane().add(txtDone, BorderLayout.CENTER);
		txtDone.setColumns(10);
		
		JButton btnNewButton = new JButton("convert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = txtDone.getText();
				String[] tokens = str.split(" ");
				str = String.format("0x%02x%02x%02x", 
						Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
				lblNewLabel.setText(str);
			}
		});
		btnNewButton.setAction(action);
		frame.getContentPane().add(btnNewButton, BorderLayout.EAST);
		
		lblNewLabel = new JLabel("0x000000");
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Convert");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
