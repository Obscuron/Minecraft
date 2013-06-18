package obscuron.mkin.core;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import obscuron.mkin.lib.BlockInfo;
import obscuron.mkin.lib.ItemInfo;
import obscuron.mkin.lib.Reference;
import cpw.mods.fml.common.FMLLog;

public class ConfigurationHandler {

    public static Configuration cfg;
    
    public static void init(File file) {
        
        cfg = new Configuration(file);
        
        try {
            cfg.load();
            
            /* Block Ids */
            BlockInfo.PROGRAMMER_ID = cfg.getBlock(BlockInfo.PROGRAMMER_NAME, BlockInfo.DEFAULT_PROGRAMMER_ID).getInt(BlockInfo.DEFAULT_PROGRAMMER_ID);
            BlockInfo.INTERFACE_ID = cfg.getBlock(BlockInfo.INTERFACE_NAME, BlockInfo.DEFAULT_INTERFACE_ID).getInt(BlockInfo.DEFAULT_INTERFACE_ID);
            
            /* Item Ids */
            ItemInfo.CARD_ID = cfg.getItem(ItemInfo.CARD_NAME, ItemInfo.DEFAULT_CARD_ID).getInt(ItemInfo.DEFAULT_CARD_ID);
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        }
        finally {
            cfg.save();
        }
        
    }

}
