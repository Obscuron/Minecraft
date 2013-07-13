package obscuron.mkin.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import obscuron.mkin.core.ItemsHandler;

public class SlotInterface extends Slot {
    
    public SlotInterface(IInventory inventory, int x, int y, int z) {
        super(inventory, x, y, z);
    }
    
    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return ItemsHandler.validEncodedCard(itemStack);
    }
}
