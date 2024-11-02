package l3oatz.eldoria.network.server;

import io.netty.buffer.ByteBuf;
import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import l3oatz.eldoria.network.AbstractServerMessageHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import utilities.Msg;

public class PacketUpStatus implements IMessage
{
	public int idpacket;
	public int str;
	public int sta;
	public int dex;
	public int ints;
	private String username;
	
	public PacketUpStatus(int idpacket, int str, int sta, int dex, int ints, String username)
 	{
		this.idpacket = idpacket;
	    this.str = str;
	    this.sta = sta;
	    this.dex = dex;
	    this.ints = ints;
	    this.username = username;
	}
  
	public PacketUpStatus() {}
  
	public void toBytes(ByteBuf buffer)
	{
	    buffer.writeInt(this.idpacket);
	    buffer.writeInt(this.str);
	    buffer.writeInt(this.sta);
	    buffer.writeInt(this.dex);
	    buffer.writeInt(this.ints);
	    ByteBufUtils.writeUTF8String(buffer, this.username);
	}
	
	public void fromBytes(ByteBuf buffer)
	{
		this.idpacket = buffer.readInt();
	    this.str = buffer.readInt();
	    this.sta = buffer.readInt();
	    this.dex = buffer.readInt();
	    this.ints = buffer.readInt();
	    this.username = ByteBufUtils.readUTF8String(buffer);
	}

	public static final class Handler extends AbstractServerMessageHandler<PacketUpStatus> 
	{
		public IMessage handleServerMessage(EntityPlayer player, PacketUpStatus message, MessageContext ctx) 
		{
			IPlayerData dtPlayer = PlayerDataCapability.getPlayerData(player);
			if(player.getName().equalsIgnoreCase(message.username))
			{
				if (dtPlayer.getPoint() > 0)
				{
					
					dtPlayer.setStr(dtPlayer.getStr() + message.str);
					dtPlayer.setSta(dtPlayer.getSta() + message.sta);
					dtPlayer.setDex(dtPlayer.getDex() + message.dex);
					dtPlayer.setInts(dtPlayer.getInts() + message.ints);
					int takepoint = message.str + message.sta + message.dex + message.ints;
					dtPlayer.setPoint(dtPlayer.getPoint() - takepoint);
					//dtPlayer.update = true;
					Msg.sys(player, "point -"+takepoint);
					PlayerDataCapability.savePlayerData((EntityPlayerMP)player);
					//PacketDispatcher.sendTo(new PacketPlayerData(player), (EntityPlayerMP)player);
				}
			}
	 		return null;
		}
 	}
}
