package obscuron.mkin.core;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabKinetics extends CreativeTabs {

    public CreativeTabKinetics(int id, String name) {
        super(id, name);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex() {
        return 1; //TODO: change icon
    }

}
