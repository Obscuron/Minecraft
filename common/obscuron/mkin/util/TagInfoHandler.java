package obscuron.mkin.util;

import java.util.Vector;

import net.minecraft.item.ItemStack;

public class TagInfoHandler {
    
    public NBTWrapper tag;
    public int id;
    public int countState;
    public int countCard;
    
    public ItemStack itemStack;
    
    public int count;
    public Vector<Integer> slotNum;
    
    public TagInfoHandler(NBTWrapper tag) {
        this.tag = tag;
        this.id = tag.getByte("id");
        this.countState = tag.getByte("countState");
        this.countCard = tag.getInt("count");
        this.itemStack = initItem();
        
        this.count = 0;
        this.slotNum = new Vector<Integer>();
    }
    
    private ItemStack initItem() {
        ItemStack item = tag.getItem("itemInfo");
        if (item != null) {
            if (id < 3) {
                item.stackTagCompound = null;
            }
            if (id < 2) {
                item.setItemDamage(0);
            }
        }
        return item;
    }
    
    public boolean isSatisfied() {
        switch (countState) {
            case 0:
                return count == countCard;
            case 1:
                return count >= countCard;
            case 2:
                return count > countCard;
            case 3:
                return count < countCard;
            case 4:
                return count <= countCard;
            default:
                return false;
        }
    }
    
    public int numRequired() {
        switch (countState) {
            case 0:
            case 1:
            case 4:
                return countCard - count;
            case 2:
                return countCard - count + 1;
            case 3:
                return countCard - count - 1;
            default:
                return 0;
        }
    }

}
