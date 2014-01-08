package lj.vgm.tileentity;

import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.Numbers;

public class TileEntityVoidGenerator extends VoidEnergyConductor {

    public TileEntityVoidGenerator() {
        super(Numbers.MAXIMUM_VOID_GENERATOR_ENERGY);
        for (int i = 0; i < 6; i++) {
            conduits[i].state = ConduitState.INPUT;
        }
    }

}
