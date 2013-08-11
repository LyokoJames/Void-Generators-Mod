package lj.vgm.core.util;

import net.minecraftforge.common.ForgeDirection;

public class ConduitDirection {
    public final ForgeDirection defaultDir;
    public ForgeDirection actualDir;
    public boolean connected = false;
    
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
}
