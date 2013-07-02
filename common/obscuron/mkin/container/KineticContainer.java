package obscuron.mkin.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import obscuron.mkin.tileentity.KineticInventoryTile;

public abstract class KineticContainer extends Container {
    
    private KineticInventoryTile tile;
    
    protected abstract int containerSize();
    
    public KineticContainer(KineticInventoryTile tile) {
        this.tile = tile;
    }

    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();
            
            if (slotIndex < containerSize()) {
                if (!this.mergeItemStack(itemStack, containerSize(), inventorySlots.size(), false)) { 
                    return null;
                }
            }
            else if (!this.mergeFromInv(itemStack)) {
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
    
    protected boolean mergeFromInv(ItemStack itemStack) {
        return this.mergeItemStack(itemStack, 0, containerSize(), false);
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return tile.isUseableByPlayer(entityPlayer);
    }

}
