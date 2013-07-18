package obscuron.mkin.tileentity;

import net.minecraft.item.ItemStack;
import obscuron.mkin.core.ItemsHandler;
import obscuron.mkin.lib.ContainerInfo;

public class TileProgrammer extends KineticInventoryTile {

    public static final int SIZE = 2;

    public TileProgrammer() {
        super();
    }
    
    @Override
    protected int invSize() {
        return SIZE;
    }

    @Override
    public String getInvName() {
        return ContainerInfo.PROGRAMMER_CONTAINER;
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }
    
    @Override
    public boolean isStackValidForSlot(int slot, ItemStack itemStack) {
        if (slot == 1) {
            return ItemsHandler.validCard(itemStack);
        }
        return true;
    }
    
}
