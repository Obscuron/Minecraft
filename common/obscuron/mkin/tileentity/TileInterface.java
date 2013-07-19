package obscuron.mkin.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import obscuron.mkin.core.ItemsHandler;
import obscuron.mkin.lib.ContainerInfo;
import obscuron.mkin.lib.ItemInfo;
import obscuron.mkin.util.InvUtil;
import obscuron.mkin.util.NBTWrapper;
import obscuron.mkin.util.TagInfoHandler;

import com.google.common.base.Objects;

public class TileInterface extends KineticInventoryTile {

    public static final int SIZE = 3*3;

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
            IInventory[] sides = InvUtil.getAdjacentInventories(worldObj, xCoord, yCoord, zCoord);
            TagInfoHandler[] tagInfo = new TagInfoHandler[invSize()];
            
            for (int i = 0; i < invSize(); i++) {
                ItemStack card = getStackInSlot(i);
                if (card == null) {
                    continue;
                }
                NBTWrapper tag = new NBTWrapper(card, ItemInfo.CARD_TAG);
                if (sides[tag.getByte("side")] == null) {
                    continue;
                }
                
                tagInfo[i] = new TagInfoHandler(tag);
                parseInv(tagInfo[i], sides[tag.getByte("side")]);
            }
            
            for (int i = 0; i < tagInfo.length; i++) {
                if (tagInfo[i] != null) {
                    if (tagInfo[i].isSatisfied()) {
                        continue;
                    }
                    
                    for (int j = 0; j < tagInfo.length; j++) {
                        if (i == j || tagInfo[j] == null) {
                            continue;
                        }
                        if (tagInfo[i].side == tagInfo[j].side) {
                            continue;
                        }
                        if (tagInfo[i].id == 4 || compare(tagInfo[i].itemStack, tagInfo[j].tag)) {
                            transferItems(tagInfo, sides, i, j);
                        }
                    }
                }
            }
        }
    }
    
    private void transferItems(TagInfoHandler[] tagInfo, IInventory[] sides, int from, int to) {
        int numReq = tagInfo[from].numRequired();
        int maxNum = Integer.MAX_VALUE;
        if (tagInfo[to].isSatisfied()) {
            maxNum = tagInfo[to].numRequired();
        }
        IInventory fromInv = sides[tagInfo[from].side];
        IInventory toInv = sides[tagInfo[to].side];
        
        if (numReq < 0) {
            int maxTransfer = -numReq;
            if (maxNum > 0) {
                maxTransfer = Math.min(-numReq, maxNum);
            }
            for (int slot : tagInfo[to].slotNum) {
                ItemStack toItem = toInv.getStackInSlot(slot);
                if (compare(toItem, tagInfo[from].tag)) {
                    int diff = toItem.getMaxStackSize() - toItem.stackSize;
                    if (diff <= 0) {
                        continue;
                    }
                    diff = Math.min(diff, maxTransfer);
                    maxTransfer = transferToSlot(toItem, toInv, slot, fromInv, tagInfo[from], diff, maxTransfer);
                }
                if (maxTransfer == 0) {
                    return;
                }
            }
            for (int i = 0; i < toInv.getSizeInventory(); i++) {
                ItemStack toItem = toInv.getStackInSlot(i);
                if (toItem == null) {
                    int diff = 64;
                    if (!(tagInfo[from].id == 4)) {
                        diff = tagInfo[from].itemStack.getMaxStackSize();
                    }
                    diff = Math.min(diff, maxTransfer);
                    maxTransfer = transferToSlot(toItem, toInv, i, fromInv, tagInfo[from], diff, maxTransfer);
                }
                
                if (maxTransfer == 0) {
                    return;
                }
            }
            
        }
        else if (numReq > 0) {
            int maxTransfer = numReq;
            if (maxNum > 0) {
                maxTransfer = Math.min(numReq, maxNum);
            }
        }
    }

    private int transferToSlot(ItemStack toItem, IInventory toInv, int toSlot, IInventory fromInv, TagInfoHandler tagInfo, int diff, int maxTransfer) {
        int curSlot = 0;
        while (curSlot < tagInfo.slotNum.size()) {
            ItemStack fromItem = fromInv.getStackInSlot(tagInfo.slotNum.get(curSlot));
            if (toItem != null) {
                if (!InvUtil.areStacksEqual(toItem, fromItem)) {
                    curSlot++;
                    continue;
                }
            }
            if (fromItem.stackSize > diff) {
                if (toItem == null) {
                    toItem = fromItem.copy();
                    toInv.setInventorySlotContents(toSlot, toItem);
                    toItem.stackSize = diff;
                }
                else {
                    toItem.stackSize += diff;
                }
                fromItem.stackSize -= diff;
                maxTransfer -= diff;
                break;
            }
            else {
                if (toItem == null) {
                    toItem = fromItem.copy();
                    toInv.setInventorySlotContents(toSlot, toItem);
                    toItem.stackSize = fromItem.stackSize;
                }
                else {
                    toItem.stackSize += fromItem.stackSize;
                }
                diff -= fromItem.stackSize;
                maxTransfer -= fromItem.stackSize;
                fromInv.setInventorySlotContents(tagInfo.slotNum.get(curSlot), null);
                tagInfo.slotNum.remove(0);
            }
            if (diff == 0) {
                break;
            }
        }
        return maxTransfer;
    }
    
    private void parseInv(TagInfoHandler tagInfo, IInventory inv) {
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack item = inv.getStackInSlot(i);
            if (compare(item, tagInfo.tag)) {
                tagInfo.slotNum.add(i);
                tagInfo.count += item.stackSize;
            }
        }
    }
    
    private boolean compare(ItemStack item, NBTWrapper tag) {
        ItemStack cardItem = tag.getItem("itemInfo");
        if (item == null) {
            return false;
        }
        if (tag.getByte("id") == 4) {
            return true;
        }
        if (item.itemID != cardItem.itemID) {
            return false;
        }
        if (tag.getByte("id") > 1 && item.getItemDamage() != cardItem.getItemDamage()) {
            return false;
        }
        if (tag.getByte("id") > 2 && !Objects.equal(item.getTagCompound(),cardItem.getTagCompound())) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean isStackValidForSlot(int slot, ItemStack itemStack) {
        return ItemsHandler.validEncodedCard(itemStack);
    }

}
