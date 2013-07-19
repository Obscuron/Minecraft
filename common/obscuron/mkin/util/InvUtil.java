package obscuron.mkin.util;

import com.google.common.base.Objects;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class InvUtil {
    
    public static final ForgeDirection[] sidesAll = {ForgeDirection.UP, ForgeDirection.DOWN, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST};
    public static final ForgeDirection[] sides = {ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST};
    
    public static IInventory[] getAdjacentInventories(World world, int x, int y, int z) {
        return getInventories(world, x, y, z, sidesAll);
    }
    
    public static IInventory[] getInventories(World world, int x, int y, int z, ForgeDirection[] offsets) {
        IInventory[] attached = new IInventory[offsets.length];
        for (int i = 0; i < offsets.length; i++){
            attached[i] = getInventory(world, x + offsets[i].offsetX, y + offsets[i].offsetY, z + offsets[i].offsetZ);
        }
        return attached;
    }
    
    public static IInventory getInventory(World world, int x, int y, int z) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if(!(tile instanceof IInventory)) {
            return null;
        }
        if(tile instanceof TileEntityChest) {
            return getChest((TileEntityChest) tile);
        }
        return (IInventory)tile;
    }
    
    public static IInventory getChest(TileEntityChest chest) {
        for (ForgeDirection offset : sides) {
            int blockId = chest.getWorldObj().getBlockId(chest.xCoord + offset.offsetX, chest.yCoord + offset.offsetY, chest.zCoord + offset.offsetZ);
            if (blockId == chest.getBlockType().blockID) {
                TileEntityChest chestOther = (TileEntityChest) chest.getWorldObj().getBlockTileEntity(chest.xCoord + offset.offsetX, chest.yCoord + offset.offsetY, chest.zCoord + offset.offsetZ);
                return new InventoryLargeChest("container.chestDouble", chestOther, chest);
            }
        }
        return chest;
    }
    
    public static boolean areStacksEqual(ItemStack stack1, ItemStack stack2) {
        if(stack1 == null || stack2 == null) {
            return stack1 == stack2;
        }
        return stack1.itemID == stack2.itemID
                && stack1.getItemDamage() == stack2.getItemDamage()
                && Objects.equal(stack1.getTagCompound(), stack2.getTagCompound());
    }

}
