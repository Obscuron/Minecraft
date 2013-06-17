package obscuron.mkin.block;

import obscuron.mkin.ModularKinetics;
import obscuron.mkin.lib.BlockInfo;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockProgrammer extends BlockContainer {

    public BlockProgrammer(int blockId) {
        super(blockId, Material.iron);
        this.setHardness(2.0F);
        this.setUnlocalizedName(BlockInfo.PROGRAMMER_NAME);
        this.setCreativeTab(ModularKinetics.tabKinetics);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

}
