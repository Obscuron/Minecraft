package obscuron.mkin.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import obscuron.mkin.container.slot.SlotInterface;
import obscuron.mkin.tileentity.TileInterface;

public class ContainerInterface extends KineticContainer {

    private final int INTERFACE_INVENTORY_ROWS = 3;
    private final int INTERFACE_INVENTORY_COLUMNS = 3;

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerInterface(InventoryPlayer inventoryPlayer, TileInterface tile) {
        super(tile);
        
        // Chest slots
        for (int r = 0; r < INTERFACE_INVENTORY_ROWS; r++) {
            for (int c = 0; c < INTERFACE_INVENTORY_COLUMNS; c++) {
                this.addSlotToContainer(new SlotInterface(tile, c + r * INTERFACE_INVENTORY_COLUMNS, 62 + c * 18, 17 + r * 18));
            }
        }
        
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
        return INTERFACE_INVENTORY_ROWS * INTERFACE_INVENTORY_COLUMNS;
    }
    
    protected boolean mergeFromInv(ItemStack itemStack) {
        for (int i = 0; i < containerSize(); i++) {
            Slot slot = (Slot) inventorySlots.get(i);
            if (!slot.getHasStack() && slot.isItemValid(itemStack)) {
                copyToSlot(itemStack, slot);
                return true;
            }
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
