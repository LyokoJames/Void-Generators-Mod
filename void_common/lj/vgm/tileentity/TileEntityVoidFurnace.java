package lj.vgm.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import lj.vgm.block.BlockVoidFurnace;
import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.Numbers;
import lj.vgm.lib.PacketStrings;
import lj.vgm.lib.Reference;
import lj.vgm.lib.Strings;
import lj.vgm.network.PacketHandler;

public class TileEntityVoidFurnace extends VoidConductorInventory implements IInventory {
    
    public static final int INVENTORY_SIZE = 2;
    
    public static final int INPUT_INVENTORY_INDEX = 0;
    public static final int OUTPUT_INVENTORY_INDEX = 1;
    
    public static final int COOK_VOID_ENERGY = 64;
    
    public static final int TOTAL_COOK_TIME = 200;
    public static final int TOTAL_BURN_TIME = 400;
    
    public boolean isBurning = false;
    public int currentBurnTime = 0;
    public int currentCookTime = 0;
    
    public TileEntityVoidFurnace() {
        super(Numbers.MAXIMUM_VOID_FURNACE_ENERGY);
        for (int i = 0; i < 6; i++) {
            conduits[i].state = ConduitState.INPUT;
        }
        inventory = new ItemStack[INVENTORY_SIZE];
    }
    
    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote) {
        boolean flag = this.currentBurnTime > 0;
        boolean flag1 = false;
        
        System.out.println("Furnace " + 
        ((FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) ?
                "Client :" : "Server :" )+ voidEnergy);
        
        if (this.currentBurnTime > 0) {
            --this.currentBurnTime;
        }

        if (!this.worldObj.isRemote){
            if (this.currentBurnTime == 0 && this.canSmelt()) {
                if (this.voidEnergy >= TileEntityVoidFurnace.COOK_VOID_ENERGY) {
                    this.useEnergy(COOK_VOID_ENERGY);
                    System.out.println("Furnace " + 
                            ((FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) ?
                                    "Client :" : "Server :" )+ voidEnergy);
                    this.currentBurnTime = TileEntityVoidFurnace.TOTAL_BURN_TIME;
                }
            }

            if (this.isBurning() && this.canSmelt()) {
                ++this.currentCookTime;

                if (this.currentCookTime >= TileEntityVoidFurnace.TOTAL_COOK_TIME) {
                    this.currentCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else {
                this.currentCookTime = 0;
            }

            if (flag != this.currentBurnTime > 0) {
                flag1 = true;
                BlockVoidFurnace.updateFurnaceBlockState(isBurning(), worldObj, xCoord, xCoord, zCoord);
            }
        }

        if (flag1) {
            this.onInventoryChanged();
        }
        this.serverSyncToClient();
        }
    }
    
    public boolean isBurning() {
        return currentBurnTime > 0;
    }
    
    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(
                    this.inventory[INPUT_INVENTORY_INDEX]);

            if (inventory[OUTPUT_INVENTORY_INDEX] == null) {
                this.inventory[OUTPUT_INVENTORY_INDEX] = itemstack.copy();
            }
            else if (this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(itemstack)) {
                inventory[OUTPUT_INVENTORY_INDEX].stackSize += itemstack.stackSize;
            }

            --this.inventory[INPUT_INVENTORY_INDEX].stackSize;

            if (this.inventory[INPUT_INVENTORY_INDEX].stackSize <= 0) {
                this.inventory[INPUT_INVENTORY_INDEX] = null;
            }
        }
    }

    private boolean canSmelt()
    {
        if (inventory[INPUT_INVENTORY_INDEX] == null) {
            return false;
        }
        else {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(inventory[INPUT_INVENTORY_INDEX]);
            if (itemstack == null) return false;
            if (inventory[OUTPUT_INVENTORY_INDEX] == null) return true;
            if (!inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(itemstack)) return false;
            int result =inventory[OUTPUT_INVENTORY_INDEX].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    @Override
    public String getInvName() {
        return Strings.CONTAINER_VOID_FURNACE_NAME;
    }
    
    public void serverSyncToClient() {
        super.serverSyncToClient();
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            PacketDispatcher.sendPacketToAllPlayers(PacketHandler.dataToPacket(
                    Reference.CHANNEL_NAME, PacketStrings.VOID_FURNACE_SYNC,
                    this.xCoord, this.yCoord, this.zCoord,
                    currentBurnTime, currentCookTime));
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);
        
        currentBurnTime = nbtTagCompound.getInteger("burnT");
        currentCookTime = nbtTagCompound.getInteger("cookT");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("burnT", currentBurnTime);
        nbtTagCompound.setInteger("cookT", currentCookTime);
    }
    
}
