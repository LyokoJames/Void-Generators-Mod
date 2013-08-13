package lj.vgm.tileentity;

import lj.vgm.core.util.ConduitSide;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityVoidConduit extends TileEntity {
    
    public ConduitSide[] conduits;
    
    public TileEntityVoidConduit() {
        conduits = new ConduitSide[6];
        for (int i = 0; i < 6; i++) {
            conduits[i] = new ConduitSide(ForgeDirection.getOrientation(i));
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
       super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
       super.readFromNBT(nbt);
    }
    @Override
    public void updateEntity() {
        
    }
}
