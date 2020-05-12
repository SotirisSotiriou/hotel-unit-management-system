package com.app.server;

import javax.swing.*;
import java.awt.BorderLayout;

public class AppFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JTextArea serverlog;
	
	public AppFrame() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		serverlog = new JTextArea();
		serverlog.setEditable(false);
		serverlog.setBounds(0, 0, 399, 149);
		panel.add(serverlog);
		
		this.setTitle("Server log");
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static JTextArea getServerlog() {
		return serverlog;
	}
	
	public static void addToServerlog(String message) {
		serverlog.setText(serverlog.getText() + System.lineSeparator() + message);
	}
}
