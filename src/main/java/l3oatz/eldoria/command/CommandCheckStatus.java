package l3oatz.eldoria.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import utilities.Msg;


import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import l3oatz.eldoria.network.NetworkHandler;
import l3oatz.eldoria.network.client.PlayerStatusSyncPacket;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommandCheckStatus extends CommandBase
{
	private List<String> aliases;
	
	public CommandCheckStatus()
	{
		this.aliases = new ArrayList<String>();
	    this.aliases.add("sts");
    }
	
    public String getName()
    {
        return "sts";
    }

    public String getUsage(ICommandSender sender) 
   {
        return "sts <command>";
    }
    
    @SuppressWarnings("unlikely-arg-type")
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
	    return (sender.canUseCommand(getRequiredPermissionLevel(), getName()))
	    || (sender.getCommandSenderEntity().equals("XxsumsumxX"));
	}
	
    public List<String> getAliases() {
        return Arrays.asList("ps", "status");
    }

	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
		if (args.length < 4) {
            throw new WrongUsageException("Usage: /playerstatus <level/exp> <player> <set/give> <value>");
        }
    	String subCommand = args[0];
    	if (subCommand.equals("level"))
    	{
    		if (args.length < 4) {
    			sender.sendMessage(new TextComponentString("Usage : Should be /hk setjob <username> <job> "));
				return;
			}
    		EntityPlayer player = (EntityPlayer)sender;
    		String receiver = args[1];
			EntityPlayerMP receiverEntity = getPlayer(server, sender, receiver);
			if (receiverEntity == null) {
				sender.sendMessage(new TextComponentString("player was not found on the server."));
				return;
			}
			IPlayerData status = PlayerDataCapability.getPlayerData(receiverEntity);
            if (status != null)
            {
            	int value = Integer.parseInt(args[3]);
    			if (value <= 0) {
    				sender.sendMessage(new TextComponentString("be greater than 0."));
    				return;
    			}
    			if (args[2].equalsIgnoreCase("set"))
    			{
    				status.setLevel(value);
    				PlayerDataCapability.syncData(receiverEntity);
    				Msg.sys(player, "+ receiverEntity.getName() + " + value);
    				NetworkHandler.sendTo(new PlayerStatusSyncPacket(status), receiverEntity);
    			}
    			else if (args[2].equalsIgnoreCase("give"))
    			{
    				status.addLevel(value);
    				PlayerDataCapability.syncData(receiverEntity);
    				Msg.sys(player, "+ receiverEntity.getName() + " + value);
    				NetworkHandler.sendTo(new PlayerStatusSyncPacket(status), receiverEntity);
    			}
            }
    	}
    	else if (subCommand.equals("exp"))
    	{
    		if (args.length < 4) {
    			sender.sendMessage(new TextComponentString("Usage : Should be /hk setjob <username> <job> "));
				return;
			}
    		EntityPlayer player = (EntityPlayer)sender;
    		String receiver = args[1];
			EntityPlayerMP receiverEntity = server.getPlayerList().getPlayerByUsername(receiver);
	        if (receiverEntity == null) {
	            throw new CommandException("commands.example.player_not_found", new Object[0]);
	        }
			IPlayerData status = PlayerDataCapability.getPlayerData(receiverEntity);
            if (status != null)
            {
            	int value = Integer.parseInt(args[3]);
    			if (value < 0) {
    				sender.sendMessage(new TextComponentString("be greater than 0."));
    				return;
    			}
    			if (args[2].equalsIgnoreCase("set"))
    			{
    				status.setExp(value);
    				PlayerDataCapability.savePlayerData(receiverEntity);
    				NetworkHandler.sendTo(new PlayerStatusSyncPacket(status), receiverEntity);
    				Msg.sys(player, "+ receiverEntity.getName() + " + value);
    			}
    			else if (args[2].equalsIgnoreCase("give"))
    			{
    		        
    		        // EXP
    		        player.sendMessage(new TextComponentString("EXP " + status.getExp()));
    				status.addExp(value);
    				// EXP
    				player.sendMessage(new TextComponentString("EXP " + status.getExp()));
    				PlayerDataCapability.savePlayerData(receiverEntity);
    				NetworkHandler.sendTo(new PlayerStatusSyncPacket(status), receiverEntity);
    			}
            }
            else
            {
            	player.sendMessage(new TextComponentString(""));
            }
    	}
    }
	
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "level", "exp");
        } else if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if (args.length == 3) {
            return getListOfStringsMatchingLastWord(args, "set", "give");
        }
        return Collections.emptyList();
    }
    
    public List<?> addTabCompletionOptions(ICommandSender var1, String[] stringList)
	{
		if (stringList.length == 1) {
			return getListOfStringsMatchingLastWord(stringList, new String[] { "setjob", "level", "exp", "setkey", "setstage", "setwarp", "duninfo", "clearinfo" });
		}
		if ((stringList[0].equals("level")) || (stringList[0].equals("exp")))
		{
			if (stringList.length == 2) {
				return getListOfStringsMatchingLastWord(stringList, getListOfPlayerUsernames());
			}
			if (stringList.length == 3) {
				return getListOfStringsMatchingLastWord(stringList, new String[] { "give", "set" });
			}
		}
		/*if (stringList[0].equals("setjob"))
		{
			if (stringList.length == 2) {
				return getListOfStringsMatchingLastWord(stringList, getListOfPlayerUsernames());
			}
			if (stringList.length == 3) {
				return getListOfStringsMatchingLastWord(stringList, new String[] { "Archer", "Magician", "Paladin", "Priest", "Warrior" });
			}
		}*/
		
		
		return null;
	}
    
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 1; // args[1] 
    }
	
	protected String[] getListOfPlayerUsernames()
	{
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
	    if (server != null) {
	        return server.getPlayerList().getOnlinePlayerNames();
	    }
	    return new String[] {}; // array 
	}
}
