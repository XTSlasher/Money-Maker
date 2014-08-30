package xtslasher.mm.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import xtslasher.mm.resources.Variables;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws Exception {
		new Frame();
	}
	
	public Frame() throws Exception {
		loadImage();
		checkVersion();
		new JFrame();
		
		setSize(800, 600);
		setTitle(Variables.title + " - " + Variables.version);
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
	
	private void checkVersion() throws Exception {
		URL github = new URL("https://raw.githubusercontent.com/XTSlasher/Money-Maker/master/VERSION.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(github.openStream()));
		
		String inputLine;
		while ((inputLine = in.readLine()) != null) 
			System.out.println(inputLine);
		in.close();
		
		/**
		
		if(version != Variables.version) {
			System.out.println("Game is outdated!");
		}
		*/
	}
}
