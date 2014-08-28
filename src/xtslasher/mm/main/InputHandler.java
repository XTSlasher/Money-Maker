package xtslasher.mm.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
		int clickedX = e.getX();
		int clickedY = e.getY();
		boolean boxClear = false;
		
		for(int i= 1;i<4;i++){
			//0 + 100*i + ((130/2)*i) - 0 + 100*i + ((130/2)*i) + 130, 400 - 400+70
			if(clickedX < 0 + 100*i + ((130/2)*i) + 130 && clickedX > 0 + 100*i + ((130/2)*i) && clickedY > 400 && clickedY < 400 + 70 && boxClear == false) {
				boxClear = true;
				if(i==1) {
					System.out.println("New Game");
					//Setup new game!
				} else if(i == 2) {
					System.out.println("Load Game");
					//Load Game Only 1 save!
				} else if(i == 3) {
					System.out.println("Options");
					screen.scene = 2;
				} else {
					System.out.println("Misplaced Box!");
				}
			}
		}
		
		boxClear = false;
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
