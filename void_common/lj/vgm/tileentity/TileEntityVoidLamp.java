package lj.vgm.tileentity;

import lj.vgm.core.util.ConduitState;

public class TileEntityVoidLamp extends VoidEnergyConductor {
    
    public boolean on = false;
    
    
    public TileEntityVoidLamp() {
        //TODO Remove Magic Number
        super(64);
        for (int i = 0; i < 6; i++) {
            conduits[i].state = ConduitState.INPUT;
        }
    }
    
    public void updateEntity() {
        if (this.voidEnergy > 0) {
            useEnergy(1);
            this.on = true;
        }
        else this.on = false;
    }
}
