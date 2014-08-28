package xtslasher.mm.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new Frame();
	}
	
	public Frame() {
		loadImage();
		new JFrame();
		
		setSize(800, 600);
		setTitle("Money Maker! Version 0.0.1");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		add(new Screen(this));
	}
	
	private void loadImage() {
		ImageIcon di = new ImageIcon("src/res/dollar.png");
		ImageIcon ti = new ImageIcon("src/res/titleScreen.png");
		Screen.dollar = di.getImage();
		Screen.title = ti.getImage();
	}
}
