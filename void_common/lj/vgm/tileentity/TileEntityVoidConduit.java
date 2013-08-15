package lj.vgm.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import lj.vgm.core.util.ConduitSide;
import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.Reference;
import lj.vgm.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityVoidConduit extends TileEntity {
    
    public ConduitSide[] conduits;
    
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
       syncTileEntity();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
       super.readFromNBT(nbt);
       for (int i = 0; i < 6; i++) {
           conduits[i].state = ConduitState.fromInt(nbt.getInteger("state"+i));
       }
       syncTileEntity();
    }
    @Override
    public void updateEntity() {
        
    }
    
    public void syncTileEntity() {
      //TODO change magic string to constant
        PacketDispatcher.sendPacketToAllPlayers(PacketHandler.dataToPacket(
                Reference.CHANNEL_NAME, 11+4*9, "voidConduit",
                this.xCoord, this.yCoord, this.zCoord,
                conduits[0].state,conduits[1].state,conduits[2].state,
                conduits[3].state,conduits[4].state,conduits[5].state));
    }
}
