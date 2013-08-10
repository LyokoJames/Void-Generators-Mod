package lj.vgm.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import lj.vgm.lib.Strings;

public class ItemItemCapsule extends ItemVGM {

    public ItemItemCapsule(int id) {
        super(id);
        this.setUnlocalizedName(Strings.ITEM_CAPSULE_UNLOC_NAME);
    }
    
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        resetCapsuleStack(itemStack);
    }
    
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (itemStack.getTagCompound() != null) {
            NBTTagCompound nbt = itemStack.getTagCompound();
            ItemStack capsuleStack = ItemStack.loadItemStackFromNBT(nbt);
            int stackSize = nbt.getInteger("stackSize");
            nbt = new NBTTagCompound();
            if (capsuleStack != null && stackSize > 0) {
                int droppedStack = Math.min(10, stackSize);
                stackSize -= droppedStack;
                capsuleStack.writeToNBT(nbt);
                nbt.setInteger("stackSize", stackSize);
                if (!world.isRemote)
                    world.spawnEntityInWorld(new EntityItem(world, player.posX,
                            player.posY, player.posZ,
                            new ItemStack(capsuleStack.getItem(), droppedStack)));
            }
            else {
                (new ItemStack(0,0,0)).writeToNBT(nbt);
                nbt.setInteger("stackSize", 0);
            }
            itemStack.setTagCompound(nbt);
        }
        else {
            NBTTagCompound nbt = new NBTTagCompound();
            (new ItemStack(0,0,0)).writeToNBT(nbt);
            nbt.setInteger("stackSize", 0);
        }
        return itemStack;
    }
    
    public void resetCapsuleStack(ItemStack itemStack) {
        NBTTagCompound nbt = new NBTTagCompound();
        (new ItemStack(Block.blockClay, 1)).writeToNBT(nbt);
        nbt.setInteger("stackSize", 101);
        itemStack.setTagCompound(nbt);
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack itemStack, 
            EntityPlayer player, List list, boolean par4) {
        NBTTagCompound nbt = itemStack.getTagCompound();
        boolean successful = false;
        if (nbt != null) {
            ItemStack capsuleStack = ItemStack.loadItemStackFromNBT(nbt);
            int stackSize = nbt.getInteger("stackSize");
            if (capsuleStack != null && stackSize > 0) {
                list.add("Currently Storing: " + capsuleStack.getDisplayName());
                String type;
                if (capsuleStack.getItem() instanceof ItemBlock)
                    type = "Block";
                else type = "Item";
                if (stackSize == 1) list.add("1 " + type + " Stored");
                else list.add(stackSize + " " + type + "s Stored");
                int tier = (int) Math.floor(Math.log(stackSize)/Math.log(2));
                tier = Math.max(0, tier - 5);
                list.add("Tier: " + tier);
                successful = true;
            }
        }
        if (!successful) {
            list.add("No Items Stored");
            list.add("Tier: 0");
        }
    }

}
