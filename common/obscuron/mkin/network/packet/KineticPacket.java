package obscuron.mkin.network.packet;

import java.io.DataInputStream;

public abstract class KineticPacket {
    
    @SuppressWarnings("rawtypes")
    public static Class[] packetType = {
        PacketProgrammer.class
    };
    
    public abstract void readInfo(DataInputStream inputStream) throws Exception;
    public abstract void execute();
    public abstract void sendPacket();

}
