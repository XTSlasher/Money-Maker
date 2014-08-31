package xtslasher.mm.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jnbt.FloatTag;
import org.jnbt.IntTag;
import org.jnbt.NBTInputStream;
import org.jnbt.NBTOutputStream;
import org.jnbt.StringTag;
import org.jnbt.Tag;

import xtslasher.mm.main.Screen;

public class GlobalFunctions {	

	public static void CreatePlayer(Screen screen) {
		String customName = JOptionPane.showInputDialog("Please input Player Name: ");
		if(customName == null || customName.length() == 0){
			customName = "Player1";
			JOptionPane.showMessageDialog(screen, "No name was entered, thus Player Name is now 'Player1");
		}
		
		for(int i=1;i<Variables.saveNames.length+1;i++) {
			Variables.fileLength++;
		}
		
		Variables.playerName = new StringTag("PlayerName", customName);
		Variables.playerMoney = new FloatTag("PlayerMoney", 0F);
		Variables.playerWorkers = new IntTag("PlayerWorkers", 0);
		Variables.updateCheck = new IntTag("UpdateChecker", 1);
		Variables.incomeMinute = new FloatTag("MinuteIncome", 0F);
		Variables.gameYear = new IntTag("GameYear", 1);
		Variables.gameWeek = new IntTag("GameWeek", 1);
		Variables.gameDay = new IntTag("GameDay", 1);
		Variables.gameTime = new IntTag("GameTime", 0);
		Variables.gameClock = new IntTag("GameClock", 0);
		
		try {
			SavePlayer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void SavePlayer() throws Exception {
		String playerPath = Variables.path + "/";
		if(!new File(playerPath).exists()) new File(playerPath).mkdirs();
		
		NBTOutputStream out = new NBTOutputStream(new FileOutputStream(new File(playerPath + "player" + Variables.ext)));		
		out.writeTag(Variables.playerName);
		out.writeTag(Variables.playerMoney);
		out.writeTag(Variables.playerWorkers);
		out.writeTag(Variables.updateCheck);
		out.writeTag(Variables.incomeMinute);
		out.writeTag(Variables.gameYear);
		out.writeTag(Variables.gameWeek);
		out.writeTag(Variables.gameDay);
		out.writeTag(Variables.gameTime);
		out.writeTag(Variables.gameClock);
		out.close();
	}
	
	public static void LoadPlayer() throws Exception {
		String playerPath = Variables.path + "/";
		boolean fileFound = new File(playerPath + "player.dat").exists();
		
		if(!fileFound) {
			return;
		}
			
		for(int i=1;i<Variables.saveNames.length+1;i++) {
			Variables.fileLength++;
		}
		
		NBTInputStream in = null;
		try {
			in = new NBTInputStream(new FileInputStream(new File(playerPath + "player" + Variables.ext)));
			
			ArrayList<Tag> tags = new ArrayList<Tag>();
			
			try {
				Tag tag;
				while((tag = in.readTag()) != null && Variables.fileLength > 0) {
					tags.add(tag);
					Variables.fileLength--;
				}
				
				in.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			Variables.playerName = (StringTag) getTag(tags, "PlayerName");
			Variables.playerMoney = (FloatTag) getTag(tags, "PlayerMoney");
			Variables.playerWorkers = (IntTag) getTag(tags, "PlayerWorkers");
			Variables.updateCheck = (IntTag) getTag(tags, "UpdateChecker");
			Variables.incomeMinute = (FloatTag) getTag(tags, "MinuteIncome");
			Variables.gameYear = (IntTag) getTag(tags, "GameYear");
			Variables.gameWeek = (IntTag) getTag(tags, "GameWeek");
			Variables.gameDay = (IntTag) getTag(tags, "GameDay");
			Variables.gameTime = (IntTag) getTag(tags, "GameTime");
			Variables.gameClock = (IntTag) getTag(tags, "GameClock");
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static Tag getTag(ArrayList<Tag> tags, String str) {
		for(Tag tag:tags) {
			if(tag.getName().equals(str)) {
				return tag;
			}
		}
		return null;
	}
}
