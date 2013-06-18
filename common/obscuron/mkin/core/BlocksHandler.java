package obscuron.mkin.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import obscuron.mkin.block.BlockInterface;
import obscuron.mkin.block.BlockProgrammer;
import obscuron.mkin.lib.BlockInfo;

public class BlocksHandler {
    
    public static Block kineticProgrammer;
    public static Block kineticInterface;
    
    public static void init() {
        kineticProgrammer = new BlockProgrammer(BlockInfo.PROGRAMMER_ID);
        kineticInterface = new BlockInterface(BlockInfo.INTERFACE_ID);
        
        GameRegistry.registerBlock(kineticProgrammer, BlockInfo.PROGRAMMER_NAME);
        GameRegistry.registerBlock(kineticInterface, BlockInfo.INTERFACE_NAME);
    }

}
