package obscuron.mkin.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import obscuron.mkin.item.ItemCard;
import obscuron.mkin.lib.ItemInfo;
import obscuron.mkin.util.NBTWrapper;

public class ItemsHandler {
    
    public static Item kineticCard;
    
    public static void init() {
        kineticCard = new ItemCard(ItemInfo.CARD_ID);
    }
    
    public static boolean validCard(ItemStack card) {
        if (card == null) {
            return false;
        }
        return card.getItem().equals(kineticCard);
    }
    
    public static boolean validEncodedCard(ItemStack card) {
        if (validCard(card)) {
            NBTWrapper tag = new NBTWrapper(card, ItemCard.TAG_NAME);
            if (tag.getByte("id") > 0) {
                return true;
            }
        }
        return false;
    }

}
