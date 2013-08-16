package lj.vgm.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.PacketStrings;
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
            e.printStackTrace();
            return;
        }
        
        System.out.println("Packet with leading string: " + packetType + 
                ", reeceived on channel: " + packet.channel);
        switch (packetType) {
            case PacketStrings.VOID_CONDUIT_SYNC:
                try {
                    PacketTypeHandler.onConduitSyncPacket (manager, inputStream,player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case PacketStrings.VOID_CONDUIT_REQUEST:
                try {
                    PacketTypeHandler.onConduitRequestPacket (manager, inputStream,player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                
            default:
                break;
        }
    }
    
    public static Packet dataToPacket(String channel,Object... data){
        int length = 0;
        
        for (Object object : data) {
            length += getObjectByteSize(object);
        }
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            for (Object object : data) {
                writeObjectToPacket(object, outputStream);
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
    
    private static int getObjectByteSize(Object object) {
        if (object instanceof Integer) {
            return 4;
        }
        else if (object instanceof String) {
            return ((String) object).length();
        }
        else if (object instanceof ForgeDirection) {
            return 4;
        }
        else if (object instanceof ConduitState) {
            return 4;
        }
        else {
            System.err.println("Tried to get byte size of unknown object: "
                    + object.getClass().toString());
            return 0;
        }
    }
    
    private static void writeObjectToPacket(Object object, DataOutputStream outputStream) throws IOException {
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
        else {
            System.err.println("Tried to write unknown object to packet: "
                    + object.getClass().toString());
        }
    }

}
