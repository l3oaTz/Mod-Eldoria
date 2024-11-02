package l3oatz.eldoria.event;

import l3oatz.eldoria.EORMainMod;
import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import l3oatz.eldoria.gui.inventory.InventoryGui;
import l3oatz.eldoria.network.NetworkHandler;
import l3oatz.eldoria.network.server.PacketOpenGuiServer;
import l3oatz.eldoria.utils.ModifierHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import utilities.Msg;
import utilities.Rn;

public class PlayerStatusHandler
{
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
			EntityPlayerMP player = (EntityPlayerMP)event.player;
			IPlayerData status = PlayerDataCapability.getPlayerData(player);
			PlayerDataCapability.syncData(player);
			float health = player.getHealth();
			float maxHealth = status.getMaxHealth();
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
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		IPlayerData status = PlayerDataCapability.getPlayerData(player);
		if (event.getGui() instanceof net.minecraft.client.gui.inventory.GuiInventory && !(Minecraft.getMinecraft()).player.isSneaking())
		{ 
			// GUI Inventory Custom GUI 
			
			event.setGui(new InventoryGui(player, player.inventory, PlayerDataCapability.getPlayerData(player).CustomInventory()));
			//NetworkHandler.sendToServer(new PacketOpenGuiServer(EORMainMod.GUI_INVENTORY));
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
	
	@SubscribeEvent
	public void onPlayerHurt(LivingHurtEvent event)
	{
		if (!event.getEntity().world.isRemote && event.getEntity() instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) event.getEntity();
		    
		    // ตรวจสอบว่าผู้โจมตีเป็น EntityPlayer และไม่เป็น null
		    if (event.getSource().getTrueSource() instanceof EntityPlayer)
		    {
		        EntityPlayer attacker = (EntityPlayer) event.getSource().getTrueSource();
		        IPlayerData dtPlayer = PlayerDataCapability.getPlayerData(attacker);
		        ItemStack mainhand = attacker.getHeldItemMainhand();
		        
		        int baseDamage = dtPlayer.getAttack();
		        int hitrate = 0;
			    int critical = 0;
			    int accuracy = dtPlayer.getAccuracy() + 100;
			    int crirate = dtPlayer.getCriRate();
			    int cridamage = dtPlayer.getCriDamage();
		        
		        // ลดค่าดาเมจตามการป้องกันของเป้าหมายถ้าเป็นผู้เล่น
		        if (entity instanceof EntityPlayer)
		        {
		            EntityPlayer target = (EntityPlayer) entity;
		            IPlayerData tdPlayer = PlayerDataCapability.getPlayerData(target);
		            baseDamage -= tdPlayer.getDefend();
		            cridamage -= tdPlayer.getReCriDamage();
		            if (tdPlayer.getEvasion() > 0) hitrate = (accuracy * 100) / tdPlayer.getEvasion();
			    	hitrate = Math.max(hitrate, 35);
			    	
			    	
			    	if (tdPlayer.getReCriRate() > 0) {
			    		critical = (crirate * 100) / tdPlayer.getReCriRate();
			    	} else {
			    		critical = 10;
			    	}
		        }
		        
		        if (crirate < 0) crirate = 0;
		        if (hitrate < 0) hitrate = 35;
			    
			    int chancehit = Rn.random(1, 100);
			    if (chancehit <= hitrate)
			    {
			    	int chcri = Rn.random(1, 100);
			    	if (chcri <= crirate)
				    {
			    		baseDamage = baseDamage * (100 + cridamage) / 100;
			    		if (entity instanceof EntityLivingBase && entity.isEntityAlive())
				    	{
			    			if (!attacker.world.isRemote)
				    		{
				    			Msg.combat(attacker, "§6§lCRITICAL §e!!!");
				    			//attacker.world.playSoundAtEntity(attacker, "wonderland:critical", 1.0F, 1.0F);
				    		}
				    	}
				    }
			    }
			    else
			    {
			    	baseDamage = 0;
			    	if (entity instanceof EntityLivingBase && entity.isEntityAlive())
			    	{
			    		if (!attacker.world.isRemote)
			    		{
			    			Msg.combat(attacker, "§f§lMISS §e!!!");
			    			//attacker.world.playSoundAtEntity(attacker, "wonderland:critical", 1.0F, 1.0F);
			    		}
			    	}
			    }
		        
		        // กำหนดค่าดาเมจใหม่
		        event.setAmount((float) baseDamage);
		    }
	    }
	}
}