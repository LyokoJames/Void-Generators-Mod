package lg.vgm.nbt;

import lj.vgm.item.ExtendedItemStack;
import lj.vgm.lib.Strings;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {
    public static NBTTagCompound itemStackToNBT(ItemStack stack , String name) {
        NBTTagCompound nbt = new NBTTagCompound(name);
        if (stack != null && stack.stackSize > 0) {
            stack.writeToNBT(nbt);
        }
        else {
            ItemStack emptyStack = new ItemStack(0,0,0);
            emptyStack.writeToNBT(nbt);
        }
        return nbt;
    }
    
    public static NBTTagCompound emptyStackToNBT(String name) {
        return itemStackToNBT(null, name);
    }
    
    public static ItemStack NBTToItemStack(NBTTagCompound nbt, String name) {
        if (nbt == null) {
            nbt = emptyStackToNBT(name);
        }
        return ItemStack.loadItemStackFromNBT(nbt);
    }
    
    public static NBTTagCompound extendedStackToNBT(ExtendedItemStack stack, String name) {
        if (stack.hasValidItemStack()) {
            NBTTagCompound nbt = new NBTTagCompound(name);
            nbt.setString(Strings.NBT_TYPE_ATRRIBUTE, Strings.EXTENDED_STACK_TYPE);
            nbt.setCompoundTag(Strings.ITEM_STACK_ATTRIBUTE, itemStackToNBT(stack.itemStack, name));
            nbt.setInteger(Strings.STACK_SIZE_ATTRIBUTE, stack.stackSize);
            return nbt;
        }
        else {
            return null;
        }
    }
    
    public static ExtendedItemStack NBTToExtendedStack(NBTTagCompound nbt, String name) {
        if(nbt != null) {
            if (nbt.getString(Strings.NBT_TYPE_ATRRIBUTE) == Strings.EXTENDED_STACK_TYPE) {
                ExtendedItemStack stack = new ExtendedItemStack();
                stack.itemStack = NBTToItemStack(nbt.getCompoundTag(Strings.ITEM_STACK_ATTRIBUTE), name);
                stack.stackSize = nbt.getInteger(Strings.STACK_SIZE_ATTRIBUTE);
                return stack;
            }
            else return null;
        }
        else return null;
    }
}


