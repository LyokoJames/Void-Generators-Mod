package lj.vgm.core.util;

import net.minecraftforge.common.ForgeDirection;

public class ConduitSide {
    public ConduitState state = ConduitState.DISCONNECTED;
    public final ForgeDirection dir;
    
    public ConduitSide (ForgeDirection dir) {
        this.dir = dir;
    }

    public void cycleState() {
        if (state == ConduitState.DISCONNECTED) {
            state = ConduitState.INPUT;
        }
        else if (state == ConduitState.INPUT) {
            state = ConduitState.OUTPUT;
        }
        else if (state == ConduitState.OUTPUT) {
            state = ConduitState.DISCONNECTED;
        }
    }
}
