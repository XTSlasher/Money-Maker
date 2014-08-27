package xtslasher.mm.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import xtslasher.mm.main.Screen.KeyTyped;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

	private Screen screen;
	private Screen.KeyTyped keyTyped;
	
	public InputHandler(Screen s) {
		screen = s;
		keyTyped = screen.new KeyTyped();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar() + ": " + e.getKeyCode());
		
		if(e.getKeyChar() == 27) {
			keyTyped.keyEsc();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
