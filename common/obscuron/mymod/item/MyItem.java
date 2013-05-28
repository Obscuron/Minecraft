package obscuron.mymod.item;

import obscuron.mymod.lib.Textures;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MyItem extends Item {

    public MyItem(int id) {
        super(id);
        setMaxStackSize(3);
        setCreativeTab(CreativeTabs.tabMisc);
        setUnlocalizedName("myItem");
    }
    
    public String getTextureFile() {
        return Textures.ITEM_TEXTURE_SHEET;
    }

}
