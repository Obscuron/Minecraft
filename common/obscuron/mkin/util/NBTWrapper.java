package obscuron.mkin.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTWrapper {
    
    private String compoundTag;
    private NBTTagCompound tag;
    
    public NBTWrapper(ItemStack itemStack, String tagName) {
        this.compoundTag = tagName;
        this.init(itemStack);
    }
    
    public NBTWrapper(NBTTagCompound tag, String tagName) {
        this.compoundTag = tagName;
        initTag(tag);
    }
    
    private void init(ItemStack itemStack) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        
        initTag(itemStack.getTagCompound());
    }
    
    public void initTag(NBTTagCompound nbtTagCompound) {
        if (!nbtTagCompound.hasKey(compoundTag)) {
            nbtTagCompound.setCompoundTag(compoundTag, new NBTTagCompound());
        }
        
        tag = nbtTagCompound.getCompoundTag(compoundTag);
    }
    
    public boolean hasTag(String tagName) {
        return tag.hasKey(tagName);
    }
    
    public void removeTag(String tagName) {
        if (hasTag(tagName)) {
            tag.removeTag(tagName);
        }
    }
    
    public String getString(String tagName) {
        if (!hasTag(tagName)) {
            tag.setString(tagName, "");
        }
        
        return tag.getString(tagName);
    }
    
    public void setString(String tagName, String value) {
        tag.setString(tagName, value);
    }
    
    public boolean getBoolean(String tagName) {
        if (!hasTag(tagName)) {
            tag.setBoolean(tagName, false);
        }
        
        return tag.getBoolean(tagName);
    }
    
    public void setBoolean(String tagName, boolean value) {
        tag.setBoolean(tagName, value);
    }
    
    public byte getByte(String tagName) {
        if (!hasTag(tagName)) {
            tag.setByte(tagName, (byte) 0);
        }
        
        return tag.getByte(tagName);
    }
    
    public void setByte(String tagName, byte value) {
        tag.setByte(tagName, value);
    }
    
    public int getInt(String tagName) {
        if (!hasTag(tagName)) {
            tag.setInteger(tagName, 0);
        }
        
        return tag.getInteger(tagName);
    }
    
    public void setInt(String tagName, int value) {
        tag.setInteger(tagName, value);
    }
    
    public ItemStack getItem(String tagName) {
        if (!hasTag(tagName)) {
            return null;
        }
        else {
            NBTTagCompound itemInfo = tag.getCompoundTag(tagName);
            return ItemStack.loadItemStackFromNBT(itemInfo);
        }
    }
    
    public void setItem(String tagName, ItemStack itemStack) {
        NBTTagCompound itemInfo = new NBTTagCompound();
        itemStack.writeToNBT(itemInfo);
        tag.setCompoundTag(tagName, itemInfo);
    }
    
}
