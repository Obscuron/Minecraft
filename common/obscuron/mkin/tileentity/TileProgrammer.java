package obscuron.mkin.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import obscuron.mkin.lib.ContainerInfo;

public class TileProgrammer extends KineticInventoryTile {

    public static final int SIZE = 9*6;

    public TileProgrammer() {
        super();
    }
    
    protected int invSize() {
        return SIZE;
    }

    @Override
    public String getInvName() {
        return ContainerInfo.PROGRAMMER_CONTAINER;
    }
    
    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!worldObj.isRemote) {
            ItemStack itemStack = getStackInSlot(0);
            if (itemStack != null) {
                int size = Math.min(itemStack.getItem().getItemStackLimit(), itemStack.stackSize * 2);
                itemStack.stackSize = size;
                onInventoryChanged();
                System.out.println("hi");
            }
//            int x = this.xCoord;
//            int y = this.yCoord;
//            int z = this.zCoord;
//            TileEntity tile = worldObj.getBlockTileEntity(x, y+1, z);
//            if (tile instanceof IInventory) {
//                IInventory inv = (IInventory) tile;
//                for (int i = 0; i < inv.getSizeInventory(); i++) {
//                    ItemStack itemStack = inv.getStackInSlot(i);
//                    if (itemStack != null) {
//                        if (itemStack.itemID == 1) {
//                            System.out.println("id of 1");
//                        }
//                    }
//                }
//            }
        }
    }
    
}
