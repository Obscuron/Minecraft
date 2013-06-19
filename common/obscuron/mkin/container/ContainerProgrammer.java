package obscuron.mkin.container;

import obscuron.mkin.tileentity.TileProgrammer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerProgrammer extends Container {

    public ContainerProgrammer(InventoryPlayer inventoryPlayer, TileProgrammer tile) {
        
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return false;
    }

}
