package obscuron.mkin.proxy;

import obscuron.mkin.lib.GuiInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiInfo.PROGRAMMER_GUI:
                return null;
            case GuiInfo.INTERFACE_GUI:
                return null;
            default: 
                return null;
        }        
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

}
