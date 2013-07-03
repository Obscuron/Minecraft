package obscuron.mkin.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import obscuron.mkin.ModularKinetics;
import obscuron.mkin.lib.ItemInfo;
import obscuron.mkin.lib.Reference;
import obscuron.mkin.util.NBTWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCard extends Item {
    
    public static final String TAG_NAME = ItemInfo.CARD_NAME;
    public static final String[] ID_LIST = {
        "Empty",
        "index 1",
        "index 2",
        "index 3"
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
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (player.isSneaking() && !world.isRemote) {
            NBTWrapper tags = new NBTWrapper(itemStack, TAG_NAME);
            tags.setByte("id", (byte) 0);
        }
        return itemStack;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        NBTWrapper tags = new NBTWrapper(itemStack, TAG_NAME);
        Byte id = tags.getByte("id");
        if (id >= ID_LIST.length) {
            id = 0;
        }
        list.add(ID_LIST[id]);
        if (id != 0) {
            int itemId = tags.getInt("itemId");
            if (itemId != 0) {
                String name = Item.itemsList[itemId].getUnlocalizedName();
                list.add(StringTranslate.getInstance().translateNamedKey(name));
            }
        }
    }
    
}
