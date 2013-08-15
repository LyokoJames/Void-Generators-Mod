package lj.vgm.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lj.vgm.core.util.ConduitState;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player player) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        String packetType;
        
        try {
            packetType = inputStream.readUTF();
        }
        catch (IOException e) {
            System.out.println("Error! Trace!");
            e.printStackTrace();
            return;
        }
        //TODO change magic string to constant
        switch (packetType) {
            case "voidConduit":
                try {
                    PacketTypeHandler.onVoidConduitPacket (manager, inputStream,player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                
            default:
                break;
        }
    }
    
    public static Packet dataToPacket(String channel, int length, Object... data){
        ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            for(Object object : data) {
                if (object instanceof Integer) {
                    outputStream.writeInt((int) object);
                }
                else if (object instanceof String) {
                    outputStream.writeUTF((String) object);
                }
                else if (object instanceof ForgeDirection) {
                    outputStream.writeInt(((ForgeDirection) object).ordinal());
                }
                else if (object instanceof ConduitState) {
                    outputStream.writeInt(((ConduitState) object).ordinal());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = channel;
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        return packet;
    }

}
