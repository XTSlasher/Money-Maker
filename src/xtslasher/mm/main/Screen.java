package xtslasher.mm.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{
	private static final long serialVersionUID = 2L;

	Thread thread = new Thread(this);
	
	private String[] titleStrings = {"New Game", "Load Game", "Options", "Huh"};
	
	Frame frame;
	public static Image dollar;
	public static Image title;
	
	private int fps = 0;
	public int scene = 0;
	
	public Screen(Frame f) {
		frame = f;
		
		frame.addKeyListener(new InputHandler(this));
		frame.addMouseListener(new InputHandler(this));
		frame.addMouseMotionListener(new InputHandler(this));
		
		thread.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println(scene);
		g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		super.paintComponent(g);
		g.setColor(Color.WHITE);

		
		
		if(scene == 0) {
			//Draw Main Menu
			g.drawImage(title, 0, 0, null);
			
			for(int i= 1;i<4;i++){
				g.setColor(Color.MAGENTA);
				g.drawRect(0 + 100*i + ((130/2)*i), 400, 130, 70);
				g.setColor(Color.YELLOW);
				g.setFont(g.getFont().deriveFont(24F));
				g.drawString(titleStrings[i-1], 0 + (101*i+(i*i+(i*(i-1)))) + ((130/2)*i), 400+(70/2)+(70/8));
			}
		} else if(scene == 1) {
			//Draw Game
			//g.drawImage(dollar, 10, 5, 60, 24, null, null);  //Draw Dollar Image
		} else if(scene == 2) {
			//Draw Options
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			g.setColor(Color.WHITE);
			g.setFont(g.getFont().deriveFont(20F));
			g.drawString("Options", frame.getWidth()/2 - 70, 50);
		}
		
		g.setFont(g.getFont().deriveFont(12F));
		g.setColor(Color.WHITE);
		//FPS
		g.drawString("FPS: " + fps, frame.getWidth() - 55, 10);
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
