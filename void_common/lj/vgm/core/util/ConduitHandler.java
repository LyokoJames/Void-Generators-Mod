package lj.vgm.core.util;

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
}
