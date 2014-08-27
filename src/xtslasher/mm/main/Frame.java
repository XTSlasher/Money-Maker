package xtslasher.mm.main;

import javax.swing.JFrame;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new Frame();
	}
	
	public Frame() {
		new JFrame();
		
		setSize(800, 600);
		setTitle("Money Maker! Version 0.0.1");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		add(new Screen(this));
	}
}
