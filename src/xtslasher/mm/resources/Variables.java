package xtslasher.mm.resources;

import org.jnbt.FloatTag;
import org.jnbt.IntTag;
import org.jnbt.StringTag;

public class Variables {
	public static final String title = "Money Maker";
	public static final int majorVersion = 0;
	public static final int minorVersion = 0;
	public static final int buildVersion = 1;
	public static final String version = "Version " + majorVersion + "." + minorVersion + "." + buildVersion;
	public static final String pathSave = "saves/";
	public static final String pathDown = "downloads/";
	public static final String ext = ".dat";
	
	public static StringTag playerName;
	public static FloatTag playerMoney;
	public static IntTag playerWorkers;
	public static IntTag updateCheck;
	public static FloatTag incomeMinute;
	public static IntTag gameYear;
	public static IntTag gameWeek;
	public static IntTag gameDay;
	public static IntTag gameTime;
	public static IntTag gameClock;
	
	public static final String[] saveNames = {"PlayerName", "PlayerMoney", "PlayerWorkers", "UpdateChecker", "MinuteIncome", "GameYear", "GameWeek", "GameDay", "GameTime", "GameClock"};
	
	public static int fileLength;
}
