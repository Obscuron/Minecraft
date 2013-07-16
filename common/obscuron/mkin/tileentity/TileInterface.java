package obscuron.mkin.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import obscuron.mkin.lib.ContainerInfo;
import obscuron.mkin.lib.ItemInfo;
import obscuron.mkin.lib.TileInfo;
import obscuron.mkin.util.InvUtil;
import obscuron.mkin.util.NBTWrapper;

public class TileInterface extends KineticInventoryTile {

    public static final int SIZE = 3*3;

    private byte slotNum;

    public TileInterface() {
        super();
    }

    @Override
    protected int invSize() {
        return SIZE;
    }

    @Override
    public String getInvName() {
        return ContainerInfo.INTERFACE_CONTAINER;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            if (nextCard()) {
                ItemStack card = getStackInSlot(slotNum);
                NBTWrapper tag = new NBTWrapper(card, ItemInfo.CARD_TAG);
                IInventory[] sides = InvUtil.getAdjacentInventories(worldObj, xCoord, yCoord, zCoord);
                if (sides[tag.getByte("side")] == null) {
                    return;
                }
                int count = countNum(tag, sides[tag.getByte("side")]);
                if (count != tag.getInt("count")) {
                    for (int i = 0; i < sides.length; i++) {
                        if (sides[i] != null) {

                        }
                    }
                }
            }
        }
    }

    private boolean nextCard() {
        byte newSlot = (byte) ((slotNum + 1) % invSize());
        while (getStackInSlot(newSlot) == null) {
            if (newSlot == slotNum) {
                slotNum = 0;
                return false;
            }
            newSlot++;
            if (newSlot >= invSize()) {
                newSlot = 0;
            }
        }
        slotNum = newSlot;
        return true;
    }
    
    private int countNum(NBTWrapper tag, IInventory inv) {
        int count = 0;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack item = inv.getStackInSlot(i);
            if (compare(item, tag)) {
                count += item.stackSize;
            }
        }
        
        return count;
    }
    
    private boolean compare(ItemStack item, NBTWrapper tag) {
        ItemStack cardItem = tag.getItem("itemInfo");
        if (item == null) {
            return false;
        }
        if (item.itemID != cardItem.itemID) {
            return false;
        }
        if (tag.getByte("id") > 1 && item.getItemDamage() != cardItem.getItemDamage()) {
            return false;
        }
        if (tag.getByte("id") > 2 && item.getTagCompound() != cardItem.getTagCompound()) {
            return false;
        }
        
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        NBTWrapper tag = new NBTWrapper(nbtTagCompound, TileInfo.INTERFACE_TAG);

        slotNum = tag.getByte("slotNum");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        NBTWrapper tag = new NBTWrapper(nbtTagCompound, TileInfo.INTERFACE_TAG);

        tag.setByte("slotNum", slotNum);
    }

}
