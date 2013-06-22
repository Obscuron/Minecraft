package obscuron.mkin.block;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import obscuron.mkin.lib.BlockInfo;
import obscuron.mkin.lib.GuiInfo;
import obscuron.mkin.lib.Reference;
import obscuron.mkin.tileentity.TileInterface;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInterface extends KineticBlockContainer {

    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public BlockInterface(int blockId) {
        super(blockId);
    }
    
    @Override
    protected String blockName() {
        return BlockInfo.INTERFACE_NAME;
    }

    @Override
    protected int guiId() {
        return GuiInfo.INTERFACE_GUI;
    }

    @Override
    protected boolean checkClass(TileEntity tile) {
        return tile instanceof TileInterface;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileInterface();
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

}
