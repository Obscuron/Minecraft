package obscuron.mkin.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import obscuron.mkin.gui.GuiInterface;
import obscuron.mkin.gui.GuiProgrammer;
import obscuron.mkin.lib.GuiInfo;
import obscuron.mkin.tileentity.TileInterface;
import obscuron.mkin.tileentity.TileProgrammer;

public class ClientProxy extends CommonProxy {

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile != null) {
            switch (ID) {
                case GuiInfo.PROGRAMMER_GUI:
                    return new GuiProgrammer(player.inventory, (TileProgrammer) tile);
                case GuiInfo.INTERFACE_GUI:
                    return new GuiInterface(player.inventory, (TileInterface) tile);
                default: 
                    return null;
            }    
        }
        return null;
    }

}
