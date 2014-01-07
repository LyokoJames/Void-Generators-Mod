package lj.vgm.core.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ExtendedItemStack {
    public int itemID;
    public int itemDamage;
    public int stackSize;
    public NBTTagCompound stackTagCompound;
    
    public ExtendedItemStack () {
    }
    
    public ExtendedItemStack (ItemStack itemStack, int stackSize){
        this.itemID = itemStack.itemID;
        this.itemDamage = itemStack.getItemDamage();
        this.stackSize = stackSize;
        stackTagCompound = itemStack.getTagCompound();
    }
    
    public ExtendedItemStack (ItemStack itemStack) {
        this(itemStack, itemStack.stackSize);
    }
    
    
    public static ExtendedItemStack loadExtendedItemStackFromNBT(NBTTagCompound par0NBTTagCompound)
    {
        ExtendedItemStack EIS = new ExtendedItemStack();
        EIS.readFromNBT(par0NBTTagCompound);
        return EIS.isValid() ? EIS : null;
    }
    
    public boolean isValid() {
        return (getItem() != null && stackSize > 0);
    }
    
    public Item getItem()
    {
        return Item.itemsList[this.itemID];
    }
    
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.itemID = par1NBTTagCompound.getInteger("id");
        this.stackSize = par1NBTTagCompound.getInteger("Count");
        this.itemDamage = par1NBTTagCompound.getInteger("Damage");

        if (this.itemDamage < 0)
        {
            this.itemDamage = 0;
        }

        if (par1NBTTagCompound.hasKey("tag"))
        {
            this.stackTagCompound = par1NBTTagCompound.getCompoundTag("tag");
        }
    }
    
    public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("id", this.itemID);
        par1NBTTagCompound.setInteger("Count", this.stackSize);
        par1NBTTagCompound.setInteger("Damage", this.itemDamage);

        if (this.stackTagCompound != null)
        {
            par1NBTTagCompound.setTag("tag", this.stackTagCompound);
        }

        return par1NBTTagCompound;
    }
    
    public ExtendedItemStack copy()
    {
        ExtendedItemStack itemstack = new ExtendedItemStack((new ItemStack(this.itemID, 0, this.itemDamage)),this.stackSize);

        if (this.stackTagCompound != null)
        {
            itemstack.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
        }

        return itemstack;
    }
    
    public ExtendedItemStack splitStack(int par1)
    {
        ExtendedItemStack itemstack = this.copy();
        itemstack.stackSize = Math.min(this.stackSize, par1);
        
        
        this.stackSize -= Math.min(this.stackSize, par1);
        return itemstack;
    }
    
    public ItemStack intoRegularStack()
    {
        if (!this.isValid()) return null;
        if (this.stackSize <= 64) {
            return (new ItemStack(this.itemID,this.stackSize,this.itemDamage));
        }
        else {
            return (new ItemStack(this.itemID,64,this.itemDamage));
        }
    }
    
    public String getDisplayName()
    {
        ItemStack regularThis = this.intoRegularStack();
        String s = this.getItem().getItemDisplayName(regularThis);

        if (this.stackTagCompound != null && this.stackTagCompound.hasKey("display"))
        {
            NBTTagCompound nbttagcompound = this.stackTagCompound.getCompoundTag("display");

            if (nbttagcompound.hasKey("Name"))
            {
                s = nbttagcompound.getString("Name");
            }
        }

        return s;
    }


}
