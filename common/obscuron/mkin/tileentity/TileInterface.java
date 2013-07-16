package obscuron.mkin.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import obscuron.mkin.core.ItemsHandler;
import obscuron.mkin.lib.ContainerInfo;
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
            nextCard();
            int numCards = 0;
            ItemStack cards[] = new ItemStack[invSize()];
            for (int i = 0; i < cards.length; i++) {
                cards[i] = getStackInSlot(i);
                if (ItemsHandler.validEncodedCard(cards[i])) {
                    numCards++;
                }
            }
            if (numCards == 0) {
                return;
            }
            
            IInventory[] sides = InvUtil.getAdjacentInventories(worldObj, xCoord, yCoord, zCoord);
            for (int i = 0; i < sides.length; i++) {
                if (sides[i] != null) {
                    
                }
            }
        }
    }
    
    private void nextCard() {
        
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
