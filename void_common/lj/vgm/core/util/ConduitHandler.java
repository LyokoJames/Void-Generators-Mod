package lj.vgm.core.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class ConduitHandler {
    
    public ConduitDirection[] conduits;
    
    public ConduitHandler() {
        conduits = new ConduitDirection[6];
        conduits[0] = new ConduitDirection(ForgeDirection.DOWN);
        conduits[1] = new ConduitDirection(ForgeDirection.UP);
        conduits[2] = new ConduitDirection(ForgeDirection.NORTH);
        conduits[3] = new ConduitDirection(ForgeDirection.SOUTH);
        conduits[4] = new ConduitDirection(ForgeDirection.WEST);
        conduits[5] = new ConduitDirection(ForgeDirection.EAST);
    }
    
    public static ConduitDirection[] getDefaultConduits() {
        ConduitDirection[] conduits = new ConduitDirection[6];
        for (int i = 0; i < 6; i++) {
            conduits[i].connected = false;
        }
        conduits[0].connected = true;
        conduits[1].connected = true;
        return conduits;
    }
    
    public ConduitHandler(NBTTagCompound nbt) {
        this();
        readFromNBT(nbt);
    }
    
    public void setConduitsRelativeToBase(ForgeDirection baseDir) {
        conduits[0].actualDir = baseDir;
        if(baseDir != ForgeDirection.DOWN && baseDir != ForgeDirection.UP){ 
            ForgeDirection rot = baseDir.getRotation(ForgeDirection.UP);
            conduits[1].setBasedOnRotation(rot);
            conduits[2].setBasedOnRotation(rot);
            conduits[3].setBasedOnRotation(rot);
            conduits[4].setBasedOnRotation(rot);
            conduits[5].setBasedOnRotation(rot);
        }
        else if (baseDir == ForgeDirection.DOWN) {
            conduits[1].setBasedOnDefault();
            conduits[2].setBasedOnDefault();
            conduits[3].setBasedOnDefault();
            conduits[4].setBasedOnDefault();
            conduits[5].setBasedOnDefault();
        }
        else {
            conduits[1].setBasedOnDefaultOpposite();
            conduits[2].setBasedOnDefaultOpposite();
            conduits[3].setBasedOnDefaultOpposite();
            conduits[4].setBasedOnDefault();
            conduits[5].setBasedOnDefault();
        }
    }
    
    public void setDirectionConnection(ForgeDirection dir, boolean set) {
        for(ConduitDirection conduit : conduits) {
            if (conduit.actualDir.equals(dir))
                conduit.connected = set;
        }
    }
    
    public boolean getDirectionConnection(ForgeDirection dir) {
        for(ConduitDirection conduit : conduits) {
            if (conduit.actualDir.equals(dir))
                return conduit.connected;
        }
        return false;
    }
    
    public void writeToNBT(NBTTagCompound nbt) {
        if (nbt != null) {
            for (int i = 0; i < 6; i++) {
                NBTTagCompound conduit = new NBTTagCompound();
                conduits[i].writeToNBT(conduit);
                nbt.setCompoundTag("conduit " + i, conduit);
            }
        }
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
        if (nbt != null) {
            for (int i = 0; i < 6; i++) {
                NBTTagCompound conduit = nbt.getCompoundTag("conduit " + i);
                if (conduit != null)
                    conduits[i].readFromNBT(conduit);
            }
        }
    }
}
