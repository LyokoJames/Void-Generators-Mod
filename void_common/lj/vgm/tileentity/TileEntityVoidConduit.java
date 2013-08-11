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
    public void writeToNBT(NBTTagCompound par1)
    {
       super.writeToNBT(par1);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1)
    {
       super.readFromNBT(par1);
    }
    
    public void setConduitDirections(ForgeDirection baseDir) {
        conduits.setConduitsRelativeToBase(baseDir);
    }
    
    public void setDirectionConnection(ForgeDirection dir, boolean set) {
        conduits.setDirectionConnection(dir, set);
    }
    
    @Override
    public void updateEntity() {
        
    }
}
