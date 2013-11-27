package lj.vgm.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.Strings;

public class TileEntityVoidFurnace extends VoidEnergyConductor implements IInventory {

    public static final int INVENTORY_SIZE = 3;
    
    private ItemStack[] inventory;
    
    public static final int FUEL_INVENTORY_INDEX = 0;
    public static final int INPUT_INVENTORY_INDEX = 1;
    public static final int OUTPUT_INVENTORY_INDEX = 2;
    
    public static final int COOK_VOID_ENERGY = 64;
    
    public static final int TOTAL_COOK_TIME = 1000;
    public static final int TOTAL_BURN_TIME = 1000;
    
    public boolean isBurning = false;
    public int currentBurnTime = 0;
    public int currentCookTime = 0;
    
    public TileEntityVoidFurnace() {
        //TODO Remove Magic Number
        super(64);
        for (int i = 0; i < 6; i++) {
            conduits[i].state = ConduitState.INPUT;
        }
        inventory = new ItemStack[INVENTORY_SIZE];
    }
    
    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean flag = this.currentBurnTime > 0;
        boolean flag1 = false;
        
        System.out.println("Furnace " + 
        ((FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) ?
                "Client :" : "Server :" )+ voidEnergy);
        
        if (this.currentBurnTime > 0)
        {
            --this.currentBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.currentBurnTime == 0 && this.canSmelt())
            {
                if (this.voidEnergy >= TileEntityVoidFurnace.COOK_VOID_ENERGY) {
                    this.useEnergy(COOK_VOID_ENERGY);
                    System.out.println("Furnace " + 
                            ((FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) ?
                                    "Client :" : "Server :" )+ voidEnergy);
                    this.serverSyncToClient();
                    this.currentBurnTime = TileEntityVoidFurnace.TOTAL_BURN_TIME;
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.currentCookTime;

                if (this.currentCookTime >= TileEntityVoidFurnace.TOTAL_COOK_TIME)
                {
                    this.currentCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                this.currentBurnTime = 0;
            }

            if (flag != this.currentBurnTime > 0)
            {
                flag1 = true;
                int currentMeta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                if (this.isBurning() && currentMeta < 6)
                    worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord,
                            currentMeta + 4, 3);
                else if (this.isBurning() && currentMeta > 5)
                    worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord,
                            currentMeta - 4, 3);
            }
        }

        if (flag1)
        {
            this.onInventoryChanged();
        }
    }
    
    public boolean isBurning() {
        return currentBurnTime > 0;
    }
    
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(
                    this.inventory[INPUT_INVENTORY_INDEX]);

            if (inventory[OUTPUT_INVENTORY_INDEX] == null)
            {
                this.inventory[OUTPUT_INVENTORY_INDEX] = itemstack.copy();
            }
            else if (this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(itemstack))
            {
                inventory[OUTPUT_INVENTORY_INDEX].stackSize += itemstack.stackSize;
            }

            --this.inventory[INPUT_INVENTORY_INDEX].stackSize;

            if (this.inventory[INPUT_INVENTORY_INDEX].stackSize <= 0)
            {
                this.inventory[INPUT_INVENTORY_INDEX] = null;
            }
        }
    }

    private boolean canSmelt()
    {
        if (inventory[INPUT_INVENTORY_INDEX] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(inventory[INPUT_INVENTORY_INDEX]);
            if (itemstack == null) return false;
            if (inventory[OUTPUT_INVENTORY_INDEX] == null) return true;
            if (!inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(itemstack)) return false;
            int result =inventory[OUTPUT_INVENTORY_INDEX].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
    
    @Override
    public int getSizeInventory() {

        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {

        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            if (itemStack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            }
            else {
                itemStack = itemStack.splitStack(amount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        if (inventory[slot] != null) {
            ItemStack itemStack = inventory[slot];
            inventory[slot] = null;
            return itemStack;
        }
        else
            return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        inventory[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    @Override
    public String getInvName() {

        return Strings.CONTAINER_VOID_FURNACE_NAME;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public void openChest() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeChest() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
        return false;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
        
        currentBurnTime = nbtTagCompound.getInteger("burnT");
        currentCookTime = nbtTagCompound.getInteger("cookT");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
            if (inventory[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);

        nbtTagCompound.setInteger("burnT", currentBurnTime);
        nbtTagCompound.setInteger("cookT", currentCookTime);
    }
    
    
}
