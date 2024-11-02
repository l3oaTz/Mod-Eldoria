package l3oatz.eldoria.event;

import l3oatz.eldoria.database.MySQLHandler;
import l3oatz.eldoria.database.PlayerAttributes;
import l3oatz.eldoria.database.PlayerDataManager;
import l3oatz.eldoria.utils.ModifierHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerStatusHandler
{
	private PlayerDataManager pDataManager = new PlayerDataManager();
	
	@SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event)
    {
		if ((!event.player.world.isRemote) && ((event.player instanceof EntityPlayerMP)))
	    {
			EntityPlayerMP player = (EntityPlayerMP) event.player;
	        String uuid = player.getUniqueID().toString();
	        
	        // Load player attributes
	        PlayerAttributes attributes = pDataManager.loadPlayerAttributes(uuid);
	        
	        if (attributes != null)
	        {
	            // Set player's attributes here if needed
	            // For example: player.setHealth(attributes.getMaxHealth());
	        	float maxHealth = attributes.getMaxHealth();
                ModifierHandler.setMaxHealth(player, maxHealth, 0);
	            System.out.println("Loaded attributes for player: " + uuid);
	        }
	        else
	        {
	        	pDataManager.saveNewPlayerAttributes(uuid);
	            System.out.println("New player attributes saved for: " + uuid);
	        }
	    }
    }
	
	@SubscribeEvent
    public void onPlayerLoggedOut(PlayerLoggedOutEvent event)
    {
		if ((!event.player.world.isRemote) && ((event.player instanceof EntityPlayerMP)))
	    {
			EntityPlayerMP player = (EntityPlayerMP)event.player;
	        String uuid = player.getUniqueID().toString();
	        
	        PlayerAttributes attributes = pDataManager.loadPlayerAttributes(uuid);
	        
	        // 
	        pDataManager.savePlayerAttributes(uuid, attributes);
	        System.out.println("Player attributes saved for: " + uuid);
	    }
    }
	
	
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event)
	{
		if ((!event.getEntity().world.isRemote) && ((event.getEntity() instanceof EntityPlayer)))
	    {
			
	    }
	}
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		// Set player health correctly after respawn.
		if (!event.player.world.isRemote  && event.player instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) event.player;
	        String uuid = player.getUniqueID().toString();
	        
	        // Load player attributes
	        PlayerAttributes attributes = pDataManager.loadPlayerAttributes(uuid);
	        
			float health = player.getHealth();
			float maxHealth = attributes.getMaxHealth();
			ModifierHandler.setMaxHealth(player, maxHealth, 0);
			if (health != maxHealth && maxHealth > 0) {
				player.setHealth(player.getMaxHealth());
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void OpenGui(GuiOpenEvent event)
	{
		EntityPlayer player = (EntityPlayer)Minecraft.getMinecraft().player;
		if (player != null)
		{ // null
			if (((event.getGui() instanceof GuiInventory)))
			{
				if ((!player.isSneaking()))
				{
					// GUI Inventory Custom GUI 
					//event.setGui(new CustomGuiInventory(player, player.inventory, status.getCustomInventory()));
				}
			} 
		}
		/*if ((event.gui instanceof InventoryGui)) {
		     PacketDispatcher.sendToServer(new PacketSetStatus(0, true));
		} else if ((event.gui instanceof GuiChat)) {
            PacketDispatcher.sendToServer(new PacketSetStatus(1, true));
        } else if ((event.gui instanceof GuiIngameMenu)) {
            PacketDispatcher.sendToServer(new PacketSetStatus(2, true));
        } else {
            PacketDispatcher.sendToServer(new PacketSetStatus(2, true));
        }*/
	}
}