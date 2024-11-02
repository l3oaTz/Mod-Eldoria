package l3oatz.eldoria.gui.inventory;

import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import utilities.Msg;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventorySlot extends Slot
{
	public int slotIndex;
	private EntityPlayer player;
	
	public InventorySlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, EntityPlayer p)
	{
	    super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	    this.player = p;
	    this.slotIndex = p_i1824_2_;
	}
	
	public int getSlotStackLimit()
	{
	    return 1;
	}
	
	public boolean isItemValid(ItemStack stack)
	{
		IPlayerData status = PlayerDataCapability.getPlayerData(this.player);
		NBTTagCompound nbt = stack.getTagCompound();
	    if (nbt == null) {
	    	nbt = new NBTTagCompound();
	    }
	    String race = nbt.getString("job");
	    int leveluse = nbt.getInteger("leveluse");
	    System.out.print(leveluse);
	    if (status.getLevel() > leveluse) {
	    	Msg.sys(this.player, "§cLevel ของคุณไม่สามารถใช้งานได้");
	    	return false;
		}
	    if (((!race.equalsIgnoreCase("")) && (race.equalsIgnoreCase(status.getPlayerClass()))) || (race.equalsIgnoreCase(""))) {
	    	Msg.sys(this.player, "§cอาชีพของคุณไม่สามารถใช้ได้");
	    	return false;
	    }
	    /*if ((this.slotIndex == 0) && ((stack.getItem() instanceof ItemAccRing))) {
	    	return true;
	    } else if ((this.slotIndex == 1) && ((stack.getItem() instanceof ItemAccEarring))) {
	    	return true;
	    } else if ((this.slotIndex == 2) && ((stack.getItem() instanceof ItemAccNecklace))) {
	    	return true;
	    } else if ((this.slotIndex == 3) && ((stack.getItem() instanceof ItemAccEarring))) {
	    	return true;
	    } else if ((this.slotIndex == 4) && ((stack.getItem() instanceof ItemAccRing))) {
	    	return true;
	    } else if ((this.slotIndex == 5) && ((stack.getItem() instanceof ItemFashionWing))) {
	    	return true;
	    } else if ((this.slotIndex == 6) && ((stack.getItem() instanceof ItemFashionMask))) {
	    	return true;
	    } else if ((this.slotIndex == 7) && ((stack.getItem() instanceof ItemFashionSet))) {
	    	return true;
	    } else if ((this.slotIndex == 8) && ((stack.getItem() instanceof ItemFashionBuff))) {
	    	return true;
	    } else if ((this.slotIndex == 9) && ((stack.getItem() instanceof ItemPet))) {
	    	return true;
	    } else if ((this.slotIndex == 10) && ((stack.getItem() instanceof ItemPetMount))) {
	    	return true;
	    } else if ((this.slotIndex == 11) && ((stack.getItem() instanceof ItemPetSupport))) {
	    	return true;
	    } else if ((this.slotIndex == 12)) {
	    	return true;
	    }*/
	    return false;
	}
	
	public boolean canTakeStack(EntityPlayer par1EntityPlayer)
	{
	    return true;
	}
	
	public void putStack(ItemStack par1ItemStack)
	{
	    if (this.inventory != null) {
	    	this.inventory.setInventorySlotContents(this.slotIndex, par1ItemStack);
	    } else {
	    	this.inventory.setInventorySlotContents(this.slotIndex, null);
	    }
	    onSlotChanged();
	}
	
	public void onSlotChanged()
	{
	    this.inventory.markDirty();
    	//PlayerData px = PlayerData.get(this.player);
	    //px.checkstats(this.player);
	}
}