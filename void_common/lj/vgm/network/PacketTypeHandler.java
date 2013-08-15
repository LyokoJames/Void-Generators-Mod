package lj.vgm.network;

import java.io.DataInputStream;
import java.io.IOException;

import lj.vgm.core.util.ConduitSide;
import lj.vgm.core.util.ConduitState;
import lj.vgm.tileentity.TileEntityVoidConduit;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.Player;

public class PacketTypeHandler {

    public static void onVoidConduitPacket(INetworkManager manager,
            DataInputStream inputStream, Player player) throws IOException {
        int x = inputStream.readInt();
        int y = inputStream.readInt();
        int z = inputStream.readInt();
        ConduitSide[] conduits = new ConduitSide[6];
        for (int i = 0; i < 6; i++) {
            conduits[i] = new ConduitSide(ForgeDirection.getOrientation(i));
            conduits[i].state = ConduitState.fromInt(inputStream.readInt());
        }

        EntityClientPlayerMP playerMP = (EntityClientPlayerMP) player;

        TileEntityVoidConduit te = (TileEntityVoidConduit) playerMP.worldObj.getBlockTileEntity(x, y, z);
        if(te != null){
            te.conduits = conduits;
            playerMP.worldObj.markBlockForUpdate(x, y, z);//this could also be the code to make a custom packet to send to all players
        }
    }

}
