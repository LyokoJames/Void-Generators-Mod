package lj.vgm.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import lj.vgm.core.util.ConduitSide;
import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.PacketStrings;
import lj.vgm.lib.Reference;
import lj.vgm.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public abstract class VoidEnergyConductor extends TileEntity{
    
    public int voidEnergy;
    public final int maxVoidEnergy;
    
    public ConduitSide[] conduits;
    public boolean initialSync = false;
    
    public VoidEnergyConductor(int maxVoidEnergy) {
        this.maxVoidEnergy = maxVoidEnergy;
        conduits = new ConduitSide[6];
        for (int i = 0; i < 6; i++) {
            conduits[i] = new ConduitSide(ForgeDirection.getOrientation(i));
        }
    }
    
    public boolean isBlocked() {
        return (this.voidEnergy >= this.maxVoidEnergy);
    }
    
    public void receiveEnergy(int energy) {
        voidEnergy = Math.min(voidEnergy + energy, maxVoidEnergy);
    }
    
    public void useEnergy(int energy) {
        voidEnergy = Math.max(voidEnergy - energy, 0);
    }
    
    
    public void sendEnergyToOutputs(int tryEnergy){
        int energy = Math.min(voidEnergy,tryEnergy);
        int divEnergy = (int) Math.floor((double) energy/(double) getNumOutputsConnectedToInputs());
        for (int i = 0; i < 6; i++) {
            if (conduits[i].state == ConduitState.OUTPUT) {
                TileEntity adjTe = worldObj.getBlockTileEntity(
                        xCoord+ForgeDirection.getOrientation(i).offsetX, 
                        yCoord+ForgeDirection.getOrientation(i).offsetY, 
                        zCoord+ForgeDirection.getOrientation(i).offsetZ);
                if (adjTe != null) {
                    if (adjTe instanceof VoidEnergyConductor) {
                        VoidEnergyConductor adjVc = (VoidEnergyConductor) adjTe;
                        if (adjVc.conduits
                                [ForgeDirection.getOrientation(i).getOpposite().ordinal()]
                                        .state == ConduitState.INPUT && !adjVc.isBlocked()) {
                            voidEnergy -= divEnergy;
                            adjVc.receiveEnergy(divEnergy);
                        }
                    }
                }
            }
        }
    }
    
    private int getNumOutputsConnectedToInputs() {
        int num = 0;
        for (int i = 0; i < 6; i++) {
            if (conduits[i].state == ConduitState.OUTPUT) {
                TileEntity adjTe = worldObj.getBlockTileEntity(
                        xCoord+ForgeDirection.getOrientation(i).offsetX, 
                        yCoord+ForgeDirection.getOrientation(i).offsetY, 
                        zCoord+ForgeDirection.getOrientation(i).offsetZ);
                if (adjTe != null) {
                    if (adjTe instanceof VoidEnergyConductor) {
                        VoidEnergyConductor adjVc = (VoidEnergyConductor) adjTe;
                        if (adjVc.conduits
                                [ForgeDirection.getOrientation(i).getOpposite().ordinal()]
                                        .state == ConduitState.INPUT) {
                            num++;
                        }
                    }
                }
            }
        }
        return num;
    }

    public int getNumOutputs() {
        int numOut = 0;
        for (int i = 0; i < 6; i++) {
            if (getConduitState(ForgeDirection.getOrientation(i)) == ConduitState.OUTPUT)
                numOut++;
        }
        return numOut;
    }
    
    public int getNumInputs() {
        int numIn = 0;
        for (int i = 0; i < 6; i++) {
            if (getConduitState(ForgeDirection.getOrientation(i)) == ConduitState.INPUT)
                numIn++;
        }
        return numIn;
    }
    
    public ConduitState getConduitState(ForgeDirection dir) {
        return conduits[dir.ordinal()].state;
    }
    
    public void setSingleInput(ForgeDirection dir) {
        for (int i = 0; i < 6; i++) {
            if (conduits[i].state == ConduitState.INPUT)
                conduits[i].state = ConduitState.DISCONNECTED;
        }
        conduits[dir.ordinal()].state = ConduitState.INPUT;
    }
    
    public void setSingleOutput(ForgeDirection dir) {
        for (int i = 0; i < 6; i++) {
            if (conduits[i].state == ConduitState.OUTPUT)
                conduits[i].state = ConduitState.DISCONNECTED;
        }
        conduits[dir.ordinal()].state = ConduitState.OUTPUT;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        //TODO make ints bytes
       super.writeToNBT(nbt);
       for (int i = 0; i < 6; i++) {
           nbt.setInteger("state"+i, conduits[i].state.ordinal());
       }
       serverSyncToClient();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
       super.readFromNBT(nbt);
       for (int i = 0; i < 6; i++) {
           conduits[i].state = ConduitState.fromInt(nbt.getInteger("state"+i));
       }
       serverSyncToClient();
    }
    
    public void serverSyncToClient() {
      //TODO change magic string to constant
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            PacketDispatcher.sendPacketToAllPlayers(PacketHandler.dataToPacket(
                    Reference.CHANNEL_NAME, PacketStrings.VOID_CONDUIT_SYNC,
                    this.xCoord, this.yCoord, this.zCoord,
                    conduits[0].state,conduits[1].state,conduits[2].state,
                    conduits[3].state,conduits[4].state,conduits[5].state));
            if (!this.initialSync) this.initialSync = true;
        }
        else System.err.println("syncFromServerToClient in TileEntityVoidConduit called from"
                + " Client.");
    }
    
    public void clientRequestServerSync() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            PacketDispatcher.sendPacketToServer(PacketHandler.dataToPacket(
                    Reference.CHANNEL_NAME, PacketStrings.VOID_CONDUIT_REQUEST,
                    this.xCoord, this.yCoord, this.zCoord));
        }
        else System.out.println("Sever Requested Its own Sync!");
    }
    

}
