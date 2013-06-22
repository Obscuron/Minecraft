package obscuron.mkin.block;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import obscuron.mkin.lib.BlockInfo;
import obscuron.mkin.lib.GuiInfo;
import obscuron.mkin.lib.Reference;
import obscuron.mkin.tileentity.TileProgrammer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockProgrammer extends KineticBlockContainer {
    
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public BlockProgrammer(int blockId) {
        super(blockId);
    }
    
    @Override
    protected String blockName() {
        return BlockInfo.PROGRAMMER_NAME;
    }
    
    @Override
    protected int guiId() {
        return GuiInfo.PROGRAMMER_GUI;
    }
    
    @Override
    protected boolean checkClass(TileEntity tile) {
        return tile instanceof TileProgrammer;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileProgrammer();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        textures = new Icon[3];
        for (int i=0; i<3; i++) {
            String suffix = i == 0 ? "Bot" : i == 1 ? "Top" : "Side";
            textures[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName2() + suffix);
        }
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        return textures[side > 1 ? 2 : side];
    }

}
