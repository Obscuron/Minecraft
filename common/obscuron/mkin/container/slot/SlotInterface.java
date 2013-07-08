package obscuron.mkin.container.slot;

import obscuron.mkin.core.ItemsHandler;
import obscuron.mkin.item.ItemCard;
import obscuron.mkin.util.NBTWrapper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotInterface extends Slot {
    
    public SlotInterface(IInventory inventory, int x, int y, int z) {
        super(inventory, x, y, z);
    }
    
    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack.getItem().equals(ItemsHandler.kineticCard)) {
            NBTWrapper tag = new NBTWrapper(itemStack, ItemCard.TAG_NAME);
            if (tag.getByte("id") > 0) {
                return true;
            }
        }
        return false;
    }
}
