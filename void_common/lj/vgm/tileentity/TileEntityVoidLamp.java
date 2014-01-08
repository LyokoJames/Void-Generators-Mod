package lj.vgm.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import lj.vgm.block.BlockVoidLamp;
import lj.vgm.core.util.ConduitState;

public class TileEntityVoidLamp extends VoidEnergyConductor {
    
    int timer = 0;
    
    public TileEntityVoidLamp() {
        //TODO Remove Magic Number
        super(64);
        for (int i = 0; i < 6; i++) {
            conduits[i].state = ConduitState.INPUT;
        }
    }
    
    @Override
    public void updateEntity() {
        super.updateEntity();
        System.out.println("Lamp: " + voidEnergy);
        if (!this.worldObj.isRemote) {
            boolean l = isLit();
        
            if (timer > 0) {
                timer--;
            }
            
            if (this.voidEnergy > 0 && timer < 1) {
                useEnergy(1);
                timer = 10;
            }
            
        if (isLit() != l) {
            BlockVoidLamp.updateLampBlockState(isLit(), worldObj, xCoord,yCoord,zCoord);
        }
        
        }
    }
    
    private boolean isLit() {
        return timer > 0;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        timer = nbtTagCompound.getInteger("Timer");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("Timer", timer);
    }
}
