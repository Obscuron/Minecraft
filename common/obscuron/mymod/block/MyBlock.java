package obscuron.mymod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MyBlock extends Block {

    public MyBlock(int id, Material material) {
        super(id, material);
        setCreativeTab(CreativeTabs.tabBlock);
    }

}
