package l3oatz.eldoria.network.server;

import io.netty.buffer.ByteBuf;
import l3oatz.eldoria.EORReference;
import l3oatz.eldoria.network.AbstractServerMessageHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import utilities.Msg;

public class PacketOpenGuiServer implements IMessage
{
	private int idpacket;
	
	public PacketOpenGuiServer() {}
	
	public PacketOpenGuiServer(int id)
	{
		this.idpacket = id;
	}
	
	public void fromBytes(ByteBuf buf)
	{
		this.idpacket = buf.readInt();
	}
	
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.idpacket);
	}
	
	public static final class Handler extends AbstractServerMessageHandler<PacketOpenGuiServer>
    {
		public IMessage handleServerMessage(EntityPlayer player, PacketOpenGuiServer message, MessageContext ctx)
        {
			EntityPlayerMP players = (EntityPlayerMP)player;
			
			/*pdata.update = true;
			if (message.id == MainMod.GUI_SKILL) {
				px.skillupdate = true;
			}*/
			Msg.sys(players, "Test" + message.idpacket);
			player.openGui(EORReference.MODID, message.idpacket, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
			return null;
        }
    }
}