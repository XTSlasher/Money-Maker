package xtslasher.mm.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import org.jnbt.IntTag;

import xtslasher.mm.resources.GlobalFunctions;
import xtslasher.mm.resources.Variables;

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
		screen.mouseX = e.getX();
		screen.mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int clickedX = e.getX();
		int clickedY = e.getY();
		boolean boxClear = false;
		
		if(screen.scene == 0){
			for(int i= 1;i<4;i++){
				//0 + 100*i + ((130/2)*i) - 0 + 100*i + ((130/2)*i) + 130, 400 - 400+70
				if(clickedX < 0 + 100*i + ((130/2)*i) + 130 && clickedX > 0 + 100*i + ((130/2)*i) && clickedY > 400 && clickedY < 400 + 70 && boxClear == false) {
					boxClear = true;
					if(i==1) {
						//Setup new game!
						GlobalFunctions.CreatePlayer(screen);
						screen.scene = 1;
					} else if(i == 2) {
						//Load Game Only 1 save!
						JOptionPane.showMessageDialog(screen, "This option has yet to be implemented", "Load Game", JOptionPane.INFORMATION_MESSAGE);
						try {
							GlobalFunctions.LoadPlayer();
							screen.scene = 1;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else if(i == 3) {
						try {
							GlobalFunctions.LoadPlayer();
							screen.scene = 2;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						System.out.println("Misplaced Box!");
					}
				}
			}
			boxClear = false;
		} else if(screen.scene == 2) {
			//frame.getWidth()/2 - 125, 90, 175, 50);
			if(clickedX < screen.frame.getWidth()/2 - 125 + 200 && clickedX > screen.frame.getWidth()/2 - 125 && clickedY < 90 + 25 + 50 && clickedY > 90 + 25 && boxClear == false) {
				boxClear = true;
				if(Variables.updateCheck.getValue() == 1) {
					Variables.updateCheck = new IntTag("UpdateChecker", 0);
				} else {
					Variables.updateCheck = new IntTag("UpdateChecker", 1);
				}
			}
			boxClear = false;
		}
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
			try {
				keyTyped.keyEsc();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if(e.getKeyCode() == 65) { //A
			keyTyped.addMoney();
		} else if(e.getKeyCode() == 67) { //C
			keyTyped.openControls(screen);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
