package l3oatz.eldoria.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class InventoryInv implements IInventory
{
    private ItemStack[] inventory; // ItemStack 
    private final String name = "custom_inv"; // Inventory
    private final int size = 45; // Inventory

    public InventoryInv()
    {
        this.inventory = new ItemStack[this.size];
        for (int i = 0; i < size; i++) {
        	this.inventory[i] = ItemStack.EMPTY; // EMPTY
        }
    }

    // getName() 
    public String getName() {
        return this.name;
    }

    public int getSizeInventory() {
        return this.size; // Inventory
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.inventory) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public ItemStack getStackInSlot(int index) {
        return this.inventory[index];
    }

    public ItemStack decrStackSize(int index, int count)
    {
        if (!this.inventory[index].isEmpty())
        {
            if (this.inventory[index].getCount() <= count) {
                ItemStack itemstack = this.inventory[index];
                this.inventory[index] = ItemStack.EMPTY;
                this.markDirty();
                return itemstack;
            } else {
                ItemStack itemstack = this.inventory[index].splitStack(count);

                if (this.inventory[index].getCount() == 0) {
                    this.inventory[index] = ItemStack.EMPTY;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return ItemStack.EMPTY;
        }
    }

    public ItemStack removeStackFromSlot(int index)
    {
        if (!this.inventory[index].isEmpty()) {
            ItemStack itemstack = this.inventory[index];
            this.inventory[index] = ItemStack.EMPTY;
            return itemstack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.inventory[index] = stack;
        if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }

    public void markDirty()
    {
        // Inventory 
    	for (int i = 0; i < getSizeInventory(); i++) {
    	    // 
    	    if ((getStackInSlot(i) != null) && (getStackInSlot(i).getMaxStackSize() == 0)) {
    	        // stackSize 0 (null
    	        this.inventory[i] = null;
    	    }
    	}
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    public void openInventory(EntityPlayer player) {
        // Inventory
    }

    public void closeInventory(EntityPlayer player) {
        // Inventory
    }

    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true; // 
    }

    public int getInventoryStackLimit() {
        return 1; // 
    }

    public void clear() {
        for (int i = 0; i < this.inventory.length; ++i) {
            this.inventory[i] = ItemStack.EMPTY;
        }
    }

    public ITextComponent getDisplayName() {
        return new TextComponentString(this.name);
    }

    public boolean hasCustomName() {
        return false; // custom inventory
    }

    public int getField(int id) {
        return 0;
    }

    public void setField(int id, int value) {
    }

    public int getFieldCount() {
        return 0;
    }

    public void clearInventory() {
        clear();
    }
    
    public void writeToNBT(NBTTagCompound compound)
    {
        NBTTagList nbtList = new NBTTagList();
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setInteger("Slot", i);
                this.inventory[i].writeToNBT(itemTag);
                nbtList.appendTag(itemTag);
            }
        }
        compound.setTag("CustomInventory", nbtList);
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        NBTTagList nbtList = compound.getTagList("CustomInventory", compound.getId());
        this.inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbtList.tagCount(); i++) {
            NBTTagCompound itemTag = nbtList.getCompoundTagAt(i);
            int slot = itemTag.getInteger("Slot");
            if (slot >= 0 && slot < this.inventory.length) {
            	this.inventory[slot] = new ItemStack(itemTag);
            }
        }
    }
}