package obscuron.mkin.container;

import obscuron.mkin.tileentity.TileProgrammer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerProgrammer extends KineticContainer {
    
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerProgrammer(InventoryPlayer inventoryPlayer, TileProgrammer tile) {
        super(tile);
        
        // Chest slots
        this.addSlotToContainer(new Slot(tile, 0, 138, 17));
        this.addSlotToContainer(new Slot(tile, 1, 138, 49));
        
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

}
