package l3oatz.eldoria.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class InventoryData extends Container
{
	public InventoryData(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryInv inventoryCustom)
	{
		/*for (int i = 0; i < 4; i++)
		{
			addSlotToContainer(new Slot(player.inventory, player.inventory.getSizeInventory() - 1 - i, 57, 29 + i * 18)
			{
				public int getSlotStackLimit()
				{
					return 1;
		        }
				
				public void onSlotChanged()
				{
					this.inventory.markDirty();
					//PlayerData pData = PlayerData.get(InventoryData.this.entityPlayer);
					//pData.checkstats(InventoryData.this.entityPlayer);
				}
			});
		}*/
		// Armor Slots (4 slots for helmet, chestplate, leggings, boots)
        for (int i = 0; i < 4; i++)
        {
            final int armorIndex = i;
            this.addSlotToContainer(new Slot(inventoryPlayer, 39 - i, 8, 8 + i * 18)
            {
                public boolean isItemValid(ItemStack stack) {
                    return stack.getItem() instanceof ItemArmor 
                        && ((ItemArmor) stack.getItem()).armorType.getIndex() == armorIndex;
                }

                public int getSlotStackLimit() {
                    return 1;
                }
            });
        }
        
		for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int x = +40 + col * 18;
                int y = 119 + row * 18;
                this.addSlotToContainer(new Slot(inventoryPlayer, col + row * 9 + 9, x, y));
            }
        }
        
		for (int col = 0; col < 9; col++) {
            int x = +40 + col * 18;
            int y = 181;
            this.addSlotToContainer(new Slot(inventoryPlayer, col, x, y));
        }
		
		/*for (int i = 0; i < 4; i++)
		{
			addSlotToContainer(new InventorySlot(inventoryCustom, i, 75, 29 + i * 18, player));
		}*/
		/*addSlotToContainer(new InventorySlot(inventoryCustom, 0, 214, 30, player));
		addSlotToContainer(new InventorySlot(inventoryCustom, 1, 214, 48, player));
		addSlotToContainer(new InventorySlot(inventoryCustom, 2, 232, 39, player));
		addSlotToContainer(new InventorySlot(inventoryCustom, 3, 250, 30, player));
		addSlotToContainer(new InventorySlot(inventoryCustom, 4, 250, 48, player));*/
	}
	
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int index)
	{
		return null;
	}
	  
	public void onContainerClosed(EntityPlayer p_75134_1_)
	{
	    super.onContainerClosed(p_75134_1_);
	}
	  
	public boolean canDragIntoSlot(Slot p_canDragIntoSlot_1_)
	{
	    return super.canDragIntoSlot(p_canDragIntoSlot_1_);
	}
}