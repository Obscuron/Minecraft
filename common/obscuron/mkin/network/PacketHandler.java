package obscuron.mkin.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import obscuron.mkin.lib.Reference;
import obscuron.mkin.network.packet.KineticPacket;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();

        if (packet.channel.equals(Reference.CHANNEL_NAME)) {
            if (side == Side.SERVER) {
                DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
                
                byte packetID;
                
                try {
                    packetID = inputStream.readByte();
                }
                catch(Exception e) {
                    e.printStackTrace();
                    return;
                }
                
                KineticPacket kPacket = buildPacket(packetID, inputStream);
                if (kPacket != null) {
                    kPacket.execute();
                }
            }
        }
    }

    private KineticPacket buildPacket(int packetID, DataInputStream inputStream) {
        @SuppressWarnings("rawtypes")
        Class packetType = KineticPacket.packetType[packetID];
        if (packetType == null) {
            return null;
        }
        KineticPacket packet = null;
        try {
            packet = (KineticPacket) packetType.newInstance();
            packet.readInfo(inputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return packet;
    }

}
