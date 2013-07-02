package obscuron.mkin.container.slot;

import obscuron.mkin.core.ItemsHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotProgrammer extends Slot {

    public SlotProgrammer(IInventory inventory, int x, int y, int z) {
        super(inventory, x, y, z);
    }
    
    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem().equals(ItemsHandler.kineticCard);
    }


}
