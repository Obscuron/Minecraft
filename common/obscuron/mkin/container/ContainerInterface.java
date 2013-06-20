package obscuron.mkin.container;

import obscuron.mkin.tileentity.TileInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerInterface extends Container {

private TileInterface tileInterface;
    
    private final int INTERFACE_INVENTORY_ROWS = 3;
    private final int INTERFACE_INVENTORY_COLUMNS = 3;

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerInterface(InventoryPlayer inventoryPlayer, TileInterface tile) {
        tileInterface = tile;
        
        // Chest slots
        for (int r = 0; r < INTERFACE_INVENTORY_ROWS; r++) {
            for (int c = 0; c < INTERFACE_INVENTORY_COLUMNS; c++) {
                this.addSlotToContainer(new Slot(tile, c + r * INTERFACE_INVENTORY_COLUMNS, 62 + c * 18, 17 + r * 18));
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
    
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();
            
            if (slotIndex < INTERFACE_INVENTORY_ROWS * INTERFACE_INVENTORY_COLUMNS) {
                if (!this.mergeItemStack(itemStack, INTERFACE_INVENTORY_ROWS * INTERFACE_INVENTORY_COLUMNS, inventorySlots.size(), false)) { 
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemStack, 0, INTERFACE_INVENTORY_ROWS * INTERFACE_INVENTORY_COLUMNS, false)) {
                return null;
            }
            
            if (itemStack.stackSize == 0) {
                slot.putStack((ItemStack) null);
            }
            else {
                slot.onSlotChanged();
            }
            
        }
        
        return newItemStack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return tileInterface.isUseableByPlayer(entityPlayer);
    }

}
