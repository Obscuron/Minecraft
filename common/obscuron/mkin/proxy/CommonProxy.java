package obscuron.mkin.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import obscuron.mkin.container.ContainerInterface;
import obscuron.mkin.container.ContainerProgrammer;
import obscuron.mkin.lib.GuiInfo;
import obscuron.mkin.lib.TileInfo;
import obscuron.mkin.tileentity.TileInterface;
import obscuron.mkin.tileentity.TileProgrammer;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile != null) {
            switch (ID) {
                case GuiInfo.PROGRAMMER_GUI:
                    return new ContainerProgrammer(player.inventory, (TileProgrammer) tile);
                case GuiInfo.INTERFACE_GUI:
                    return new ContainerInterface(player.inventory, (TileInterface) tile);
                default: 
                    return null;
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileProgrammer.class, TileInfo.PROGRAMMER_TILE);
        GameRegistry.registerTileEntity(TileInterface.class, TileInfo.INTERFACE_TILE);
    }

}
