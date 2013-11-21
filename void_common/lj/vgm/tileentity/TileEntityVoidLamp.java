package lj.vgm.tileentity;

import lj.vgm.core.util.ConduitState;

public class TileEntityVoidLamp extends VoidEnergyConductor {
    
    public boolean on = false;
    int timer = 0;
    
    public TileEntityVoidLamp() {
        //TODO Remove Magic Number
        super(64);
        for (int i = 0; i < 6; i++) {
            conduits[i].state = ConduitState.INPUT;
        }
    }
    
    public void updateEntity() {
        if (this.voidEnergy > 0 && timer < 1) {
            useEnergy(1);
            timer = 10;
        }
        if (timer > 0) {
            this.on = true;
            this.worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
            timer--;
        }
        else {
            this.on = false;
            this.worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
        }
    }
}
