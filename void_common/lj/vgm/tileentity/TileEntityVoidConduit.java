package lj.vgm.tileentity;

import lj.vgm.core.util.ConduitSide;
import lj.vgm.core.util.ConduitState;
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
        //TODO make ints bytes
       super.writeToNBT(nbt);
       for (int i = 0; i < 6; i++) {
           nbt.setInteger("state"+i, conduits[i].state.ordinal());
       }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
       super.readFromNBT(nbt);
       for (int i = 0; i < 6; i++) {
           conduits[i].state = ConduitState.fromInt(nbt.getInteger("state"+i));
       }
    }
    @Override
    public void updateEntity() {
        
    }
}
