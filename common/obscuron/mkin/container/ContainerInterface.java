package obscuron.mkin.container;

import obscuron.mkin.tileentity.TileInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerInterface extends Container {

    public ContainerInterface(InventoryPlayer inventoryPlayer, TileInterface tile) {
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return false;
    }

}
