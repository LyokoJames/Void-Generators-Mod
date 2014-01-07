package lj.vgm.core.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ExtendedItemStack {
    public int itemID;
    public int itemDamage;
    public int stackSize;
    public NBTTagCompound stackTagCompound;
    
    
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


}
