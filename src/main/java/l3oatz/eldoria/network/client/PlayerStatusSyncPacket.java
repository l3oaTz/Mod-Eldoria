package l3oatz.eldoria.network.client;

import io.netty.buffer.ByteBuf;
import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PlayerStatusSyncPacket implements IMessage
{
    private NBTTagCompound data;

    // Constructor IPlayerStatus
    public PlayerStatusSyncPacket(IPlayerData status)
    {
        this.data = (NBTTagCompound)PlayerDataCapability.PLAYER_DATA_CAPABILITY.getStorage().writeNBT(PlayerDataCapability.PLAYER_DATA_CAPABILITY, status, null);
    }

    // Constructor IMessage
    public PlayerStatusSyncPacket() {}

    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeTag(buf, data);
    }

    public void fromBytes(ByteBuf buf)
    {
        data = ByteBufUtils.readTag(buf);
    }

    public static class Handler implements IMessageHandler<PlayerStatusSyncPacket, IMessage>
    {
        public IMessage onMessage(PlayerStatusSyncPacket message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                EntityPlayer player = Minecraft.getMinecraft().player;
                IPlayerData status = PlayerDataCapability.getPlayerData(player);
                if (player != null)
                {
	                if (status != null && message.data != null) {
	                	try {
	                		PlayerDataCapability.PLAYER_DATA_CAPABILITY.getStorage().readNBT(PlayerDataCapability.PLAYER_DATA_CAPABILITY, status, null, message.data);
	                		System.out.println("Sync Player data to Client :" + message.data);
	                	} catch (Exception e) {
                            // Exception Logging 
                            e.printStackTrace();
                        }
	                }
                }
            });
            return null;
        }
    }
}