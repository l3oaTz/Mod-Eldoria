package l3oatz.eldoria.event.client;

import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import l3oatz.eldoria.event.AutoThirdPerson;
import l3oatz.eldoria.network.NetworkHandler;
import l3oatz.eldoria.network.client.PlayerStatusSyncPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TickEventHandler
{
	private Minecraft mc = Minecraft.getMinecraft();
	public boolean SKIP_FRONT_VIEW = true;
	
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		if(event.phase != Phase.START) return;
		
		
		
	}
	
	@SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event)
    {
		AutoThirdPerson.enterThirdPerson();
		if (this.mc.gameSettings.thirdPersonView != 1) {
			//AutoThirdPerson.oldCameraMode = -1;
			this.mc.gameSettings.thirdPersonView = 1;
			KeyBinding.setKeyBindState((Minecraft.getMinecraft()).gameSettings.keyBindTogglePerspective.getKeyCode(), false); 
		}
		if (!event.player.world.isRemote)
		{
			EntityPlayer player = event.player;
    		IPlayerData status = PlayerDataCapability.getPlayerData(player);
    		
    		if (status != null)
    		{
    			if ((status.getLevel() < 50))
    			{
    				if ((status.getExp() >= 10 + (status.getLevel() * 10)))
    				{
	    			    // 
	    			    int newLevel = status.getLevel() + 1;
	    			    int newPoint = status.getPoint() + 1;
	    			    
	    			    status.setExp(0);
	    				status.setLevel(newLevel);
	    				status.setPoint(newPoint);
	    				PlayerDataCapability.savePlayerData(player);
	    				NetworkHandler.sendTo(new PlayerStatusSyncPacket(status), (EntityPlayerMP) player);
	    				//NetworkHandler.INSTANCE.sendTo(new PlayerStatusSyncPacket(status), (EntityPlayerMP)player);
	    				player.sendMessage(new TextComponentString("LevelUP " + status.getLevel()));
	    				//player.world.playSoundAtEntity(player, "pirateth:levelup", 1.0F, 1.0F);
    				}
    			}
    			else
    			{
    				//player.sendMessage(new TextComponentString("EXP " + status.getExp()));
    			}
    		}
		}
    }
}