package l3oatz.eldoria.network.client;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import l3oatz.eldoria.database.PlayerAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncPlayerDataPacket implements IMessage
{
	private String uuid;
    private String job;
    private int level;
    private int exp;
    private int coin;
    private int cash;

    public SyncPlayerDataPacket()
    {
        // Required constructor
    }

    public SyncPlayerDataPacket(String uuid, String job, int level, int exp, int coin, int cash)
    {
        this.uuid = uuid;
        this.job = job;
        this.level = level;
        this.exp = exp;
        this.coin = coin;
        this.cash = cash;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
    	uuid = buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString();
        job = buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString();
        level = buf.readInt();
        exp = buf.readInt();
        coin = buf.readInt();
        cash = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
    	byte[] uuidBytes = uuid.getBytes(StandardCharsets.UTF_8);
        byte[] jobBytes = job.getBytes(StandardCharsets.UTF_8);
        
        buf.writeInt(uuidBytes.length);
        buf.writeBytes(uuidBytes);
        buf.writeInt(jobBytes.length);
        buf.writeBytes(jobBytes);
        buf.writeInt(level);
        buf.writeInt(exp);
        buf.writeInt(coin);
        buf.writeInt(cash);
    }

    public static class Handler implements IMessageHandler<SyncPlayerDataPacket, IMessage> {
        @Override
        public IMessage onMessage(SyncPlayerDataPacket message, MessageContext ctx) {
            // Handle the packet on the client side
            // Use Minecraft's main thread to update GUI or player data
            Minecraft.getMinecraft().addScheduledTask(() -> {
                // Code to update player GUI with the received data
                //PlayerGUI.updateData(message.uuid, message.job, message.level, message.exp, message.coin, message.cash);
            	// เข้าถึงผู้เล่นปัจจุบัน
                EntityPlayer player = Minecraft.getMinecraft().player;

                // ตรวจสอบว่า UUID ตรงกันหรือไม่
                if (player.getUniqueID().toString().equals(message.uuid)) {
                    // สร้าง PlayerAttributes ใหม่จากข้อมูลที่ได้รับ
                    PlayerAttributes attributes = new PlayerAttributes(
                        message.uuid,
                        message.job,
                        message.level,
                        message.exp,
                        message.stpoint,
                        message.skpoint
                        // เพิ่มข้อมูลที่คุณต้องการที่นี่
                    );

                    // ที่นี่ คุณสามารถทำการแสดงข้อมูลใน GUI หรือส่วนอื่น ๆ ของเกมได้
                    System.out.println("Updated player attributes for: " + message.uuid);
                }
            });
            return null;
        }
    }
}