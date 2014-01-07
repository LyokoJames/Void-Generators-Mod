package lj.vgm.item;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import lj.vgm.block.ModBlocks;
import lj.vgm.core.util.ExtendedItemStack;
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
            ExtendedItemStack capsuleStack = ExtendedItemStack.loadExtendedItemStackFromNBT(nbt);
            nbt = new NBTTagCompound();
            if (capsuleStack != null && capsuleStack.isValid()) {
                ExtendedItemStack droppedStack = capsuleStack.splitStack(10); 
                capsuleStack.writeToNBT(nbt);
                if (!world.isRemote)
                    world.spawnEntityInWorld(new EntityItem(world, player.posX,
                            player.posY, player.posZ,
                            droppedStack.intoRegularStack()));
            }
            //else {
             //   (new ItemStack(0,0,0)).writeToNBT(nbt);
             //   nbt.setInteger("stackSize", 0);
            //}
            itemStack.setTagCompound(nbt);
        }
        //else {
          //  NBTTagCompound nbt = new NBTTagCompound();
          //  (new ItemStack(0,0,0)).writeToNBT(nbt);
          //  nbt.setInteger("stackSize", 0);
        //}
        return itemStack;
    }
    
    public void resetCapsuleStack(ItemStack itemStack) {
        NBTTagCompound nbt = new NBTTagCompound();
        (new ExtendedItemStack((new ItemStack(ModBlocks.voidEnergyLamp, 0)), 128)).writeToNBT(nbt);
        itemStack.setTagCompound(nbt);
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack itemStack, 
            EntityPlayer player, List list, boolean par4) {
        NBTTagCompound nbt = itemStack.getTagCompound();
        boolean successful = false;
        if (nbt != null) {
            ExtendedItemStack capsuleStack = ExtendedItemStack.loadExtendedItemStackFromNBT(nbt);
            if (capsuleStack != null && capsuleStack.isValid()) {
                list.add("Currently Storing: " + capsuleStack.getDisplayName());
                String type;
                if (capsuleStack.getItem() instanceof ItemBlock)
                    type = "Block";
                else type = "Item";
                if (capsuleStack.stackSize == 1) list.add("1 " + type + " Stored");
                else list.add(capsuleStack.stackSize + " " + type + "s Stored");
                int tier = (int) Math.floor(Math.log(capsuleStack.stackSize)/Math.log(2));
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
