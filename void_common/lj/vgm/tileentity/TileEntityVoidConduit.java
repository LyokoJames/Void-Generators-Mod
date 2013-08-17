package lj.vgm.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import lj.vgm.block.ModBlocks;
import lj.vgm.core.util.ConduitSide;
import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.PacketStrings;
import lj.vgm.lib.Reference;
import lj.vgm.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityVoidConduit extends TileEntity {
    
    public ConduitSide[] conduits;
    public boolean initialSync = false;
    
    public TileEntityVoidConduit() {
        conduits = new ConduitSide[6];
        for (int i = 0; i < 6; i++) {
            conduits[i] = new ConduitSide(ForgeDirection.getOrientation(i));
        }
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
    @Override
    public void updateEntity() {
        
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
}
