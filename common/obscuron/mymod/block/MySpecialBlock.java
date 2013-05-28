package obscuron.mymod.block;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import obscuron.mymod.lib.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class MySpecialBlock extends Block {

    public MySpecialBlock(int id) {
        super(id, Material.ground);
        setUnlocalizedName("specialBlock");
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    @Override
    public boolean onBlockActivated(World world, int bx, int by, int bz,
            EntityPlayer playerEntity, int u, float px, float py, float pz) {
        Random random = new Random();
        int randomInt1 = random.nextInt();
        int randomInt2 = random.nextInt();
       
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
                outputStream.writeInt(randomInt1);
                outputStream.writeInt(randomInt2);
        } catch (Exception ex) {
                ex.printStackTrace();
        }
       
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = Reference.CHANNEL_NAME;
        packet.data = bos.toByteArray();
        packet.length = bos.size();
       
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
                // We are on the server side.
        } else if (side == Side.CLIENT) {
                // We are on the client side.
                EntityClientPlayerMP player = (EntityClientPlayerMP) playerEntity;
                player.sendQueue.addToSendQueue(packet);
        } else {
                // We are on the Bukkit server.
        }
       
        return false;
    }

}
