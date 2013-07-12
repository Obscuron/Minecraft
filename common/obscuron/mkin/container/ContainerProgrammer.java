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
        this.addSlotToContainer(new Slot(tile, 0, 126, 17));
        this.addSlotToContainer(new SlotProgrammer(tile, 1, 126, 65));
        
        // Player inventory slots
        for (int r = 0; r < PLAYER_INVENTORY_ROWS; r++) {
            for (int c = 0; c < PLAYER_INVENTORY_COLUMNS; c++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, c + r * PLAYER_INVENTORY_COLUMNS + 9, 8 + c * 18, 99 + r * 18));
            }
        }
        
        // Player action bar slots
        for (int c = 0; c < PLAYER_INVENTORY_COLUMNS; c++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, c, 8 + c * 18, 157));
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
            copyToSlot(itemStack, slot1);
            return true;
        }
        else if (!slot0.getHasStack()) {
            copyToSlot(itemStack, slot0);
            return true;
        }
        return false;
    }
    
    private void copyToSlot(ItemStack itemStack, Slot slot) {
        ItemStack newItemStack = itemStack.copy();
        newItemStack.stackSize = 1;
        slot.putStack(newItemStack);
        slot.onSlotChanged();
        itemStack.stackSize -= 1;
    }

}
