package obscuron.mkin.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import obscuron.mkin.ModularKinetics;
import obscuron.mkin.lib.BlockInfo;
import obscuron.mkin.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInterface extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public BlockInterface(int blockId) {
        super(blockId, Material.iron);
        this.setHardness(2.0F);
        this.setUnlocalizedName(BlockInfo.INTERFACE_NAME);
        this.setCreativeTab(ModularKinetics.tabKinetics);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        textures = new Icon[6];
        for (int i=0; i<6; i++) {
            textures[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName2() + i);
        }
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        return textures[side];
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

}
