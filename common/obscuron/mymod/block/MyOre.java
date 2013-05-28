package obscuron.mymod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MyOre extends Block {

    public MyOre(int id, Material material) {
        super(id, material);
        setHardness(4.0F);
        setStepSound(Block.soundStoneFootstep);
        setUnlocalizedName("genericOre");
        setCreativeTab(CreativeTabs.tabBlock);
    }

}
