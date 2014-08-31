package xtslasher.mm.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jnbt.IntTag;

import xtslasher.mm.resources.GlobalFunctions;
import xtslasher.mm.resources.Variables;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;

	private Integer majorI;
	private boolean majorO = false;
	private boolean majorN = false;
	private Integer minorI;
	private boolean minorO = false;
	private boolean minorN = false;
	private Integer buildI;
	private boolean buildO = false;
	private boolean buildN = false;
	
	public static void main(String[] args) throws Exception {
		new Frame();
	}
	
	public Frame() throws Exception {
		loadImage();
		GlobalFunctions.LoadPlayer();
		
		if(Variables.updateCheck != null) {
			if(Variables.updateCheck.getValue() != null) {
				if(Variables.updateCheck.getValue() == 1) {
					checkVersion();
				}
			} else {
				checkVersion();
			}
		} else {
			checkVersion();
		}
		
		
		new JFrame();
		
		setSize(800, 600);
		setTitle(Variables.title + " - " + Variables.version);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
		URL github = new URL("https://raw.githubusercontent.com/XTSlasher/Money-Maker/master/VERSION.md");
		BufferedReader in = new BufferedReader(new InputStreamReader(github.openStream()));
		
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			if(inputLine.contains("Major")) majorI = new Integer(inputLine.substring(7));
			if(inputLine.contains("Minor")) minorI = new Integer(inputLine.substring(7));
			if(inputLine.contains("Build")) buildI = new Integer(inputLine.substring(7));
		}
		in.close();
		
		if(buildI > Variables.buildVersion) {
			buildO = true; 
			buildN = false;
		} else if(buildI == Variables.buildVersion) {
			buildO = false;
			buildN = false;
		} else {
			buildO = false;
			buildN = true;
		}
		
		if(minorI > Variables.minorVersion) {
			minorO = true; 
			minorN = false;
		} else if(minorI == Variables.minorVersion) {
			minorO = false;
			minorN = false;
		} else {
			minorO = false;
			minorN = true; 
		}
		
		if(majorI > Variables.majorVersion) {
			majorO = true; 
			majorN = false;
		} else if(majorI == Variables.majorVersion) {
			majorO = false;
			majorN = false;
		} else { 
			majorO = false;
			majorN = true; 
		}
		
		if(majorO || minorO || buildO) {
			JOptionPane.showMessageDialog(this, "Your game is outdated!");
			
			checkFile();
		} else if(majorN || minorN || buildN) {
			JOptionPane.showMessageDialog(this, "Your game hasn't been released yet!");
		} else {
			JOptionPane.showMessageDialog(this, "Your game is up to date!");
		}
		
		Variables.updateCheck = new IntTag("UpdateChecker", 1);
	}
	
	public void checkFile() throws Exception {
		JOptionPane.showMessageDialog(this, "Will auto download newest version!");
		String localFileName = "testFile.jar"; //The file that will be saved on your computer
		String downFileName = "testJar.jar";
		
		URL link = new URL("https://raw.githubusercontent.com/XTSlasher/Money-Maker/master/releases/" + downFileName); //The file that you want to download
		
		//Code to download
		InputStream in = new BufferedInputStream(link.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		{
		   out.write(buf, 0, n);
		}
		out.close();
		in.close();
		
		byte[] response = out.toByteArray();
		if(!new File(Variables.pathDown).exists()) {
			new File(Variables.pathDown).mkdirs();
		}
		
		FileOutputStream fos = new FileOutputStream(Variables.pathDown + localFileName);
		fos.write(response);
		fos.close();
		//End download code
		
		System.out.println("Finished");
	}
}
