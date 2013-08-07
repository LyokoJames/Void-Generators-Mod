package lj.vgm.item;

import net.minecraft.item.ItemStack;

public class ExtendedItemStack {
    public int stackSize;
    public ItemStack itemStack;
    private int tier;
    
    public ExtendedItemStack(ItemStack _itemStack, int _stackSize){
        if (_itemStack != null && _stackSize > 0) {
            itemStack = _itemStack.copy();
            itemStack.stackSize = 1;
            stackSize = _stackSize;
        }
        else {
            itemStack = null;
            stackSize = -1;
        }
    }
    
    public ExtendedItemStack(ItemStack stack) {
        this(stack, stack.stackSize);
    }
    
    public ExtendedItemStack() {
        this(null, 0);
    }

    public boolean hasValidItemStack() {
        if (itemStack != null && stackSize > 0)
            return true;
        else return false;
    }
    
    public ItemStack takeFromExtendedStack(int numItems) {
        if (hasValidItemStack()) {
            ItemStack stack = itemStack.copy();
            if (this.stackSize < numItems) {
                numItems = this.stackSize;
            }
            this.stackSize -= numItems;
            stack.stackSize = numItems;
            return stack;
        }
        else return null;
    }

    public int getTier() {
        calculateTier();
        return tier;
    }

    public void calculateTier() {
        tier = (int) Math.floor((Math.log(stackSize)/Math.log(2)));
        tier -= 5;
        tier = Math.max(tier, 0);
    }
}
