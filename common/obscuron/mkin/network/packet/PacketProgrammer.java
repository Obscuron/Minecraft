package obscuron.mkin.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import obscuron.mkin.lib.Reference;
import obscuron.mkin.tileentity.TileInterface;

public class PacketProgrammer extends KineticPacket {
    
    public static final byte packetID = 0;
    
    public int dimension;
    
    public int x;
    public int y;
    public int z;
    
    @Override
    public void readInfo(DataInputStream inputStream) throws Exception {
        dimension = inputStream.readInt();
        
        x = inputStream.readInt();
        y = inputStream.readInt();
        z = inputStream.readInt();
        
    }
    
    public void readInfo(TileInterface tileProgrammer) {
        dimension = tileProgrammer.getWorldObj().provider.dimensionId;
        x = tileProgrammer.xCoord;
        y = tileProgrammer.yCoord;
        z = tileProgrammer.zCoord;
    }
    
    @Override
    public void execute() {
        World world = DimensionManager.getWorld(dimension);
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile instanceof TileInterface) {
            TileInterface tileProgrammer = (TileInterface) tile;
            ItemStack itemStack = tileProgrammer.getStackInSlot(0);
            if (itemStack != null) {
                int size = 2 * itemStack.stackSize;
                if (size > itemStack.getItem().getItemStackLimit()) {
                    size = itemStack.getItem().getItemStackLimit();
                }
                itemStack.stackSize = size;
                tileProgrammer.onInventoryChanged();
            }
        }
    }

    @Override
    public void sendPacket() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(bos);
        
        try {
            outputStream.writeByte(packetID);
            outputStream.writeInt(dimension);
            outputStream.writeInt(x);
            outputStream.writeInt(y);
            outputStream.writeInt(z);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = Reference.CHANNEL_NAME;
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        
        PacketDispatcher.sendPacketToServer(packet);
    
    }

    

}
