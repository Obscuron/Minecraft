package obscuron.mkin.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import obscuron.mkin.block.BlockInterface;
import obscuron.mkin.block.BlockProgrammer;
import obscuron.mkin.lib.BlockInfo;

public class BlocksHandler {
    
    public static Block kineticProgrammer = new BlockProgrammer(BlockInfo.PROGRAMMER_ID);
    public static Block kineticInterface = new BlockInterface(BlockInfo.INTERFACE_ID);
    
    public static void init() {        
        GameRegistry.registerBlock(kineticProgrammer, BlockInfo.PROGRAMMER_NAME);
        GameRegistry.registerBlock(kineticInterface, BlockInfo.INTERFACE_NAME);
    }

}
