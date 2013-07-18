package obscuron.mkin.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import obscuron.mkin.core.ItemsHandler;
import obscuron.mkin.lib.ContainerInfo;
import obscuron.mkin.lib.ItemInfo;
import obscuron.mkin.util.InvUtil;
import obscuron.mkin.util.NBTWrapper;
import obscuron.mkin.util.TagInfoHandler;

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
                        if (i == j) {
                            continue;
                        }
                        if (compare(tagInfo[i].itemStack, tagInfo[j].tag)) {
                            
                        }
                    }
                }
            }
        }
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
        if (tag.getByte("id") > 2 && item.getTagCompound() != cardItem.getTagCompound()) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean isStackValidForSlot(int slot, ItemStack itemStack) {
        return ItemsHandler.validEncodedCard(itemStack);
    }

}
