package lj.vgm.tileentity;

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
        if (this.voidEnergy > 0 && timer < 1) {
            useEnergy(1);
            timer = 10;
        }
        if (timer > 0) {
            this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord,
                    1, 3);
            timer--;
        }
        else {
            this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord,
                    0, 3);
        }
    }
}
