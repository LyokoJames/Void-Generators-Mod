package lj.vgm.tileentity;

import lj.vgm.core.util.ConduitHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityVoidConduit extends TileEntity {
    
    public ConduitHandler conduits;
    
    public TileEntityVoidConduit() {
        conduits = new ConduitHandler();
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
       super.writeToNBT(nbt);
       NBTTagCompound handler = new NBTTagCompound();
       conduits.writeToNBT(handler);
       nbt.setCompoundTag("handler", handler);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
       super.readFromNBT(nbt);
       NBTTagCompound handler = nbt.getCompoundTag("handler");
       if (handler != null)
           conduits.readFromNBT(handler);
    }
    
    public void setConduitDirections(ForgeDirection baseDir) {
        conduits.setConduitsRelativeToBase(baseDir);
    }
    
    public void setDirectionConnection(ForgeDirection dir, boolean set) {
        conduits.setDirectionConnection(dir, set);
    }
    
    public boolean getDirectionConnection(ForgeDirection dir) {
        return conduits.getDirectionConnection(dir);
    }
    
    @Override
    public void updateEntity() {
        
    }
}
