package obscuron.mkin.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import obscuron.mkin.container.ContainerInterface;
import obscuron.mkin.container.ContainerProgrammer;
import obscuron.mkin.lib.GuiInfo;
import obscuron.mkin.tileentity.TileInterface;
import obscuron.mkin.tileentity.TileProgrammer;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiInfo.PROGRAMMER_GUI:
                return new ContainerProgrammer(player.inventory, (TileProgrammer) world.getBlockTileEntity(x, y, z));
            case GuiInfo.INTERFACE_GUI:
                return new ContainerInterface(player.inventory, (TileInterface) world.getBlockTileEntity(x, y, z));
            default: 
                return null;
        }        
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

}
