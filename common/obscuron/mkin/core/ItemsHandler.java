package obscuron.mkin.core;

import net.minecraft.item.Item;
import obscuron.mkin.item.ItemCard;
import obscuron.mkin.lib.ItemInfo;

public class ItemsHandler {
    
    public static Item kineticCard;
    
    public static void init() {
        kineticCard = new ItemCard(ItemInfo.CARD_ID);
    }

}
