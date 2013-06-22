package obscuron.mkin.container;

import obscuron.mkin.tileentity.TileProgrammer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerProgrammer extends KineticContainer {
    
    private final int PROGRAMMER_INVENTORY_ROWS = 6;
    private final int PROGRAMMER_INVENTORY_COLUMNS = 9;

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerProgrammer(InventoryPlayer inventoryPlayer, TileProgrammer tile) {
        super(tile);
        
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
    
    @Override
    protected int containerSize() {
        return PROGRAMMER_INVENTORY_ROWS * PROGRAMMER_INVENTORY_COLUMNS;
    }

}
