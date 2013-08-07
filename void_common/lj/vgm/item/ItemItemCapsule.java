package lj.vgm.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import lg.vgm.nbt.NBTHelper;
import lj.vgm.VoidGenerators;
import lj.vgm.lib.Strings;

public class ItemItemCapsule extends ItemVGM {

    public ItemItemCapsule(int id) {
        super(id);
        this.setCreativeTab(VoidGenerators.tabsVGM);
        this.setUnlocalizedName(Strings.ITEM_CAPSULE_UNLOC_NAME);
    }
    
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        resetCapsuleStack(itemStack);
    }
    
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (itemStack.getTagCompound() != null) {
            ExtendedItemStack capsuleStack = NBTHelper.
                    NBTToExtendedStack(itemStack.getTagCompound(), "");
            if (capsuleStack != null) {
                ItemStack ejectedStack =  capsuleStack.takeFromExtendedStack(10);
                if (!world.isRemote) {
                    world.spawnEntityInWorld(new EntityItem(world,
                        player.posX, player.posY, player.posZ,
                       ejectedStack));
                }
                itemStack.setTagCompound(NBTHelper.extendedStackToNBT(capsuleStack, ""));
            }
        }
        return itemStack;
    }
    
    public void resetCapsuleStack(ItemStack itemStack) {
        ExtendedItemStack stack = new ExtendedItemStack(
                new ItemStack(Item.bakedPotato, 1), 1024);
        itemStack.setTagCompound(NBTHelper.extendedStackToNBT(stack, ""));
    }

}
