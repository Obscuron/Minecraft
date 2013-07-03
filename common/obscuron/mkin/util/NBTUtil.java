package obscuron.mkin.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtil {

    public static void initTag(ItemStack itemStack) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }
    
    public static boolean hasTag(ItemStack itemStack, String tagName) { 
        if (itemStack.hasTagCompound()) {
            return itemStack.getTagCompound().hasKey(tagName);
        }
        return false;
    }
    
    public static void removeTag(ItemStack itemStack, String tagName) {
        if (hasTag(itemStack, tagName)) {
            itemStack.getTagCompound().removeTag(tagName);
        }
    }
    
    public static String getString(ItemStack itemStack, String tagName) {
        initTag(itemStack);
        
        if (!itemStack.getTagCompound().hasKey(tagName)) {
            itemStack.getTagCompound().setString(tagName, "");
        }
        
        return itemStack.getTagCompound().getString(tagName);
    }
    
    public static void setString(ItemStack itemStack, String tagName, String value) {
        initTag(itemStack);
        
        itemStack.getTagCompound().setString(tagName, value);
    }
    
    public static boolean getBoolean(ItemStack itemStack, String tagName) {
        initTag(itemStack);
        
        if (!itemStack.getTagCompound().hasKey(tagName)) {
            itemStack.getTagCompound().setBoolean(tagName, false);
        }
        
        return itemStack.getTagCompound().getBoolean(tagName);
    }
    
    public static void setBoolean(ItemStack itemStack, String tagName, boolean value) {
        initTag(itemStack);
        
        itemStack.getTagCompound().setBoolean(tagName, value);
    }
    
    public static byte getByte(ItemStack itemStack, String tagName) {
        initTag(itemStack);
        
        if (!itemStack.getTagCompound().hasKey(tagName)) {
            itemStack.getTagCompound().setByte(tagName, (byte) 0);
        }
        
        return itemStack.getTagCompound().getByte(tagName);
    }
    
    public static void setByte(ItemStack itemStack, String tagName, byte value) {
        initTag(itemStack);
        
        itemStack.getTagCompound().setByte(tagName, value);
    }
    
    public static int getInt(ItemStack itemStack, String tagName) {
        initTag(itemStack);
        
        if (!itemStack.getTagCompound().hasKey(tagName)) {
            itemStack.getTagCompound().setInteger(tagName, 0);
        }
        
        return itemStack.getTagCompound().getInteger(tagName);
    }
    
    public static void setInt(ItemStack itemStack, String tagName, int value) {
        initTag(itemStack);
        
        itemStack.getTagCompound().setInteger(tagName, value);
    }
    
}
