package lj.vgm.core.util;

import net.minecraftforge.common.ForgeDirection;

public class ConduitSide {
    public ConduitState state = ConduitState.DISCONNECTED;
    public final ForgeDirection dir;
    
    public ConduitSide (ForgeDirection dir) {
        this.dir = dir;
    }
}
