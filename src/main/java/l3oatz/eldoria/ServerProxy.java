package l3oatz.eldoria;

import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import l3oatz.eldoria.gui.inventory.InventoryData;
import l3oatz.eldoria.gui.inventory.InventoryGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy implements IGuiHandler
{

	public void preInit() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public void onServerStarting() {
		// TODO Auto-generated method stub
		
	}
	
	public EntityPlayer getPlayerEntity(MessageContext ctx)
    {
    	return ctx.getServerHandler().player;
    }

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		// TODO Auto-generated method stub
		//IPlayerData pData = PlayerDataCapability.getPlayerData(player);
		if (ID == EORMainMod.GUI_INVENTORY)
    	{
    		return new InventoryData(player, player.inventory, PlayerDataCapability.getPlayerData(player).CustomInventory());
    	}
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		// TODO Auto-generated method stub
		//IPlayerData pData = PlayerDataCapability.getPlayerData(player);
		if (ID == EORMainMod.GUI_INVENTORY) {
            return new InventoryGui(player, player.inventory, PlayerDataCapability.getPlayerData(player).CustomInventory());
        }
		return null;
	}
	
	

}
