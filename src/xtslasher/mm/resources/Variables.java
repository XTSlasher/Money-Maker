package xtslasher.mm.resources;

import org.jnbt.FloatTag;
import org.jnbt.IntTag;
import org.jnbt.StringTag;

public class Variables {
	public static final String title = "Money Maker";
	public static final String version = "Version 0.0.1";
	public static final String path = "saves/";
	public static final String ext = ".dat";
	
	public static StringTag playerName;
	public static FloatTag playerMoney;
	public static IntTag playerWorkers;
	
	public static final String[] saveNames = {"PlayerName", "PlayerMoney", "PlayerWorkers"};
	
	public static int fileLength;
}
