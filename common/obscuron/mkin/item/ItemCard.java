package obscuron.mkin.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import obscuron.mkin.ModularKinetics;
import obscuron.mkin.lib.ItemInfo;
import obscuron.mkin.lib.Reference;
import obscuron.mkin.util.NBTWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCard extends Item {
    
    private static final String TAG_NAME = ItemInfo.CARD_NAME;
    private static final String[] ID_LIST = {
        "Empty"
    };

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
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        NBTWrapper tags = new NBTWrapper(itemStack, TAG_NAME);
        Byte id = tags.getByte("id");
        if (id > ID_LIST.length) {
            id = 0;
        }
        list.add(ID_LIST[id]);
    }
    
}
