package lj.vgm.core.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class ConduitDirection {
    private ForgeDirection defaultDir;
    public ForgeDirection actualDir;
    public boolean connected = false;
    
    public ForgeDirection getDefaultDir() {
        return defaultDir;
    }
    
    public ConduitDirection(ForgeDirection defaultDir) {
        this.defaultDir = defaultDir;
    }
    
    public void setBasedOnRotation(ForgeDirection rot) {
        if(defaultDir == rot || defaultDir == rot.getOpposite())
            actualDir = defaultDir;
        else
            actualDir = rot.getRotation(defaultDir);
    }
    
    public void setBasedOnDefault() {
        actualDir = defaultDir;
    }
    
    public void setBasedOnDefaultOpposite() {
        actualDir = defaultDir.getOpposite();
    }
    
    public void writeToNBT(NBTTagCompound nbt) {
        if (nbt != null) {
            nbt.setByte("defDir", (byte) defaultDir.ordinal());
            nbt.setByte("actDir", (byte) actualDir.ordinal());
            nbt.setBoolean("con", connected);
        }
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
        if (nbt != null) {
            defaultDir = ForgeDirection.getOrientation(nbt.getByte("defDir"));
            actualDir = ForgeDirection.getOrientation(nbt.getByte("actDir"));
            connected = nbt.getBoolean("con");
        }
    }
}
