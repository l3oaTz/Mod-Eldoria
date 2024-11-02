package utilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class Msg 
{
	public static String mss(String n)
	{
		return n.replaceAll("&", "§");
	}
	
	public static void sys(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentString("§bSystem §b\u25BA " + txt.replaceAll("&", "§")));
	}
	
	public static void eve(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentString("§bEvent §b\u25BA " + txt.replaceAll("&", "§")));
	}
	
	public static void combat(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentString("§4Combat §b\u25BA " + txt.replaceAll("&", "§")));
	}
	
	public static void txt(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentTranslation(txt.replaceAll("&", "§"), new Object[0]));
	}
	
	public static void dungeon(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentString("§6Dungeon §b\u25BA " + txt.replaceAll("&", "§")));
	}
	
	public static void guild(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentString("§6Guild §b\u25BA " + txt.replaceAll("&", "§")));
	}
	
	public static void GuildWar(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentString("§6GuildWar §b\u25BA " + txt.replaceAll("&", "§")));
	}
	
	public static void quest(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentString("§bQuest §b\u25BA " + txt.replaceAll("&", "§")));
	}
	
	public static void skill(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentString("§6Skill §b\u25BA " + txt.replaceAll("&", "§")));
	}
	
	public static void receivedaily(EntityPlayer p, String txt)
	{
		p.sendMessage(new TextComponentString("§bReceive Daily §b\u25BA " + txt.replaceAll("&", "§")));
	}
}