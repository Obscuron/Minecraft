package obscuron.mkin.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import obscuron.mkin.ModularKinetics;
import obscuron.mkin.lib.ItemInfo;
import obscuron.mkin.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCard extends Item {

    public ItemCard(int id) {
        super(id);
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.setUnlocalizedName(ItemInfo.CARD_NAME);
        this.setCreativeTab(ModularKinetics.tabKinetics);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName2());
    }
    
    public String getUnlocalizedName2() {
        return this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1);
    }
    
}
