package lj.vgm.inventory;

import lj.vgm.tileentity.TileEntityVoidFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerVoidFurnace extends Container {
    
    private TileEntityVoidFurnace voidFurnaceTE;
    
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;
    
    public ContainerVoidFurnace(InventoryPlayer inventoryPlayer, TileEntityVoidFurnace tileEntityVoidFurnace){
        voidFurnaceTE = tileEntityVoidFurnace;
        
        // Add the input slot to the container
        this.addSlotToContainer(new Slot(voidFurnaceTE, TileEntityVoidFurnace.INPUT_INVENTORY_INDEX, 56, 17));

        // Add the output results slot to the container
        this.addSlotToContainer(new SlotVoidFurnace(voidFurnaceTE, TileEntityVoidFurnace.OUTPUT_INVENTORY_INDEX, 116, 35));
        
     // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 84 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 142));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
        /*Slot slot = (Slot) inventorySlots.get(slotIndex);
        ItemStack itemStack = slot.getStack();
        return itemStack;*/
        ItemStack emptyStack = null;
        return emptyStack;
    }
}
