package obscuron.mkin.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import obscuron.mkin.container.slot.SlotProgrammer;
import obscuron.mkin.tileentity.TileProgrammer;

public class ContainerProgrammer extends KineticContainer {
    
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerProgrammer(InventoryPlayer inventoryPlayer, TileProgrammer tile) {
        super(tile);
        
        // Chest slots
        this.addSlotToContainer(new Slot(tile, 0, 138, 17));
        this.addSlotToContainer(new SlotProgrammer(tile, 1, 138, 49));
        
        // Player inventory slots
        for (int r = 0; r < PLAYER_INVENTORY_ROWS; r++) {
            for (int c = 0; c < PLAYER_INVENTORY_COLUMNS; c++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, c + r * PLAYER_INVENTORY_COLUMNS + 9, 8 + c * 18, 84 + r * 18));
            }
        }
        
        // Player action bar slots
        for (int c = 0; c < PLAYER_INVENTORY_COLUMNS; c++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, c, 8 + c * 18, 142));
        }
        
    }
    
    @Override
    protected int containerSize() {
        return 2;
    }
    
    @Override
    protected boolean mergeFromInv(ItemStack itemStack) {
        Slot slot0 = (Slot) inventorySlots.get(0);
        Slot slot1 = (Slot) inventorySlots.get(1);
        if (!slot1.getHasStack() && slot1.isItemValid(itemStack)) {
            ItemStack newItemStack = itemStack.copy();
            newItemStack.stackSize = 1;
            slot1.putStack(newItemStack);
            slot1.onSlotChanged();
            itemStack.stackSize -= 1;
            return true;
        }
        else if (!slot0.getHasStack()) {
            ItemStack newItemStack = itemStack.copy();
            newItemStack.stackSize = 1;
            slot0.putStack(newItemStack);
            slot0.onSlotChanged();
            itemStack.stackSize -= 1;
            return true;
        }
        return false;
    }

}
