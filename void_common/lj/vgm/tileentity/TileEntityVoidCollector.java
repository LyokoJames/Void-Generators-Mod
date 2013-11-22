 package lj.vgm.tileentity;

import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.Numbers;

public class TileEntityVoidCollector extends VoidEnergyConductor {
    
    int counter = 9;
    
    public TileEntityVoidCollector() {
        super(Numbers.MAXIMUN_VOID_COLLECTOR_ENERGY);
        for (int i = 0; i < 6; i++) {
            conduits[i].state = ConduitState.OUTPUT;
        }
    }
    
    @Override
    public void updateEntity() {
        if (counter <= 0) {
            this.receiveEnergy(1);
            System.out.println("Collector Before: " + voidEnergy);
            this.sendEnergyToOutputs(maxVoidEnergy);
            System.out.println("Collector After: " + voidEnergy);
            counter = 9;
        }
        else counter--;
    }

}
