package lj.vgm.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player player) {
    }
    
    public Packet dataToPacket(String channel, int length, Object... data){
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
