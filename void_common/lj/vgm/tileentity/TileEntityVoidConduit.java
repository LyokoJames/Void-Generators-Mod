package lj.vgm.tileentity;

import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.Numbers;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityVoidConduit extends VoidEnergyConductor {
    
    public TileEntityVoidConduit() {
        super(Numbers.MAXIMUN_VOID_CONDUIT_ENERGY);
    }

    public void determineConnections(ForgeDirection dir) {
        setSingleInput(dir);
        setSingleOutput(dir.getOpposite());
        
        determineConnections();
    }
    
    public void determineConnections() {
        
        for (int i = 0; i < 6; i++) {
            TileEntity adjTe = worldObj.getBlockTileEntity(
                    xCoord+ForgeDirection.getOrientation(i).offsetX, 
                    yCoord+ForgeDirection.getOrientation(i).offsetY, 
                    zCoord+ForgeDirection.getOrientation(i).offsetZ);
            if (adjTe != null) {
                if (adjTe instanceof TileEntityVoidConduit) {
                    TileEntityVoidConduit adjVc = (TileEntityVoidConduit) adjTe;
                    if (adjVc.conduits
                            [ForgeDirection.getOrientation(i).getOpposite().ordinal()]
                                    .state == ConduitState.OUTPUT) {
                        setSingleInput(ForgeDirection.getOrientation(i));
                    }
                    if (adjVc.conduits
                            [ForgeDirection.getOrientation(i).getOpposite().ordinal()]
                                    .state == ConduitState.INPUT) {
                        setSingleOutput(ForgeDirection.getOrientation(i));
                    }
                }
            }
        }
    }
    
    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote) {
        System.out.println("Conduit: " + voidEnergy);
        this.sendEnergyToOutputs(maxVoidEnergy);
        }
    }

    public void flipStates() {
        for (int i = 0; i < 6; i++) {
            conduits[i].flipState();
        }
    }
    
    
}
