package xtslasher.mm.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{
	private static final long serialVersionUID = 2L;

	Thread thread = new Thread(this);
	
	Frame frame;
	Image dollar;
	
	private int fps = 0;
	public int scene = 0;
	
	public Screen(Frame f) {
		frame = f;
		
		frame.addKeyListener(new InputHandler(this));
		frame.addMouseListener(new InputHandler(this));
		frame.addMouseMotionListener(new InputHandler(this));
		
		initScreen();
		thread.start();
	}
	
	private void initScreen() {
		loadImage();
	}
	
	private void loadImage() {
		ImageIcon di = new ImageIcon("src/res/dollar.png");
		dollar = di.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		super.paintComponent(g);

		//FPS
		g.drawString("FPS: " + fps, frame.getWidth() - 55, 10);
		
		if(scene == 0) {
			//Draw Main Menu
		} else if(scene == 1) {
			//Draw Game
			//g.drawImage(dollar, 10, 5, 60, 24, null, null);  //Draw Dollar Image
		} else if(scene == 2) {
			//Draw Options
		}
		
		
	}
	
	@Override
	public void run() {
		System.out.println("Game is Running!");
		
		long lastFrame = System.currentTimeMillis();
		int frames = 0;
		
		while(true) {
			repaint();
			
			frames++;
			
			if(System.currentTimeMillis() - 1000 >= lastFrame){
				fps = frames;
				frames = 0;
				lastFrame = System.currentTimeMillis();
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class KeyTyped {
		public void keyEsc() {
			System.exit(0);
		}
	}
}
