package obscuron.mkin.core;

import obscuron.mkin.lib.BlockInfo;
import obscuron.mkin.lib.ContainerInfo;
import obscuron.mkin.lib.ItemInfo;
import obscuron.mkin.lib.Reference;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LanguageHandler {
    
    public static void init() {
        /* Adds creative tab names to the language registry */
        LanguageRegistry.instance().addStringLocalization("itemGroup." + Reference.MOD_ID, Reference.MOD_NAME);
        
        /* Adds block names to the language registry */
        LanguageRegistry.addName(BlocksHandler.kineticProgrammer, BlockInfo.PROGRAMMER_FULLNAME);
        LanguageRegistry.addName(BlocksHandler.kineticInterface, BlockInfo.INTERFACE_FULLNAME);
        
        /* Adds item names to the language registry */
        LanguageRegistry.addName(ItemsHandler.kineticCard, ItemInfo.CARD_FULLNAME);
        
        /* Adds container names to the language registry */
        LanguageRegistry.instance().addStringLocalization(ContainerInfo.PROGRAMMER_CONTAINER, BlockInfo.PROGRAMMER_FULLNAME);
        LanguageRegistry.instance().addStringLocalization(ContainerInfo.INTERFACE_CONTAINER, BlockInfo.INTERFACE_FULLNAME);
    }

}
