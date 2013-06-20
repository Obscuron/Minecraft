package obscuron.mkin.container;

import obscuron.mkin.tileentity.TileProgrammer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerProgrammer extends Container {
    
    private TileProgrammer tileProgrammer;
    
    private final int PROGRAMMER_INVENTORY_ROWS = 6;
    private final int PROGRAMMER_INVENTORY_COLUMNS = 9;

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerProgrammer(InventoryPlayer inventoryPlayer, TileProgrammer tile) {
        tileProgrammer = tile;
        
        // Chest slots
        for (int r = 0; r < PROGRAMMER_INVENTORY_ROWS; r++) {
            for (int c = 0; c < PROGRAMMER_INVENTORY_COLUMNS; c++) {
                this.addSlotToContainer(new Slot(tile, c + r * PROGRAMMER_INVENTORY_COLUMNS, 8 + c * 18, 18 + r * 18));
            }
        }
        
        // Player inventory slots
        for (int r = 0; r < PLAYER_INVENTORY_ROWS; r++) {
            for (int c = 0; c < PLAYER_INVENTORY_COLUMNS; c++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, c + r * PLAYER_INVENTORY_COLUMNS + 9, 8 + c * 18, 140 + r * 18));
            }
        }
        
        // Player action bar slots
        for (int c = 0; c < PLAYER_INVENTORY_COLUMNS; c++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, c, 8 + c * 18, 198));
        }
        
    }
    
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();
            
            if (slotIndex < PROGRAMMER_INVENTORY_ROWS * PROGRAMMER_INVENTORY_COLUMNS) {
                if (!this.mergeItemStack(itemStack, PROGRAMMER_INVENTORY_ROWS * PROGRAMMER_INVENTORY_COLUMNS, inventorySlots.size(), false)) { 
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemStack, 0, PROGRAMMER_INVENTORY_ROWS * PROGRAMMER_INVENTORY_COLUMNS, false)) {
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
        return tileProgrammer.isUseableByPlayer(entityPlayer);
    }

}
