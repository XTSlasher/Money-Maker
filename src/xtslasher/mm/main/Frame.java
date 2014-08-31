package xtslasher.mm.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
		ImageIcon di = createImageIcon("/res/dollar.png", "DollarPicture");
		ImageIcon ti = createImageIcon("/res/titleScreen.png", "TitleImage");
		Screen.dollar = di.getImage();
		Screen.title = ti.getImage();
	}
	
	protected ImageIcon createImageIcon(String path, String description) {
		URL imgURL = getClass().getResource(path);
		if(imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
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
			
			JOptionPane.showMessageDialog(this, "Please Visit: http://adf.ly/rdP65 to download the latest version. \nSorry for the inconvience.");
			//if(majorO) checkFile("major");
			//if(minorO) checkFile("minor");
			//if(buildO) checkFile("build");
		} else if(majorN || minorN || buildN) {
			JOptionPane.showMessageDialog(this, "Your game hasn't been released yet!");
		} else {
			JOptionPane.showMessageDialog(this, "Your game is up to date!");
		}
		
		Variables.updateCheck = new IntTag("UpdateChecker", 1);
	}
	
	public void checkFile(String version) throws Exception {
		JOptionPane.showMessageDialog(this, "Will auto download newest version!");
		String localFileName = "MoneyMaker_Latest.jar"; //The file that will be saved on your computer
		String localVersion = Variables.version;
		int majorD = 0;
		int minorD = 0;
		int buildD = 0;
		String downVersion = "";
		
		if(version == "major") majorD = Variables.majorVersion + 1;
		if(version == "minor") minorD = Variables.minorVersion + 1;
		if(version == "build") buildD = Variables.buildVersion + 1;
		
		downVersion = "Version" + majorD + "" + minorD + "" + buildD;
		
		System.out.println("Local: " + localVersion + ", Downloading: " + downVersion);
		
		String downFileName = "MoneyMaker" + downVersion + ".jar";
		
		System.out.println("Attempting to download: " + downFileName);
		
		URL link = new URL("https://github.com/XTSlasher/Money-Maker/raw/master/releases/" + downFileName); //The file that you want to download
		
		System.out.println(checkExists(link.toString()));
		
		if(checkExists(link.toString())) {
			JOptionPane.showMessageDialog(this, "Sorry, the version on file has not been released yet..");			
		} else {
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
	
	public boolean checkExists(String URL) {
		try {
		      HttpURLConnection.setFollowRedirects(false);
		      HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();
		      con.setRequestMethod("HEAD");
		      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		    }
		    catch (Exception e) {
		       e.printStackTrace();
		       return false;
		    }
	}
}
