package obscuron.mkin.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import obscuron.mkin.container.ContainerProgrammer;
import obscuron.mkin.lib.ContainerInfo;
import obscuron.mkin.lib.GuiInfo;
import obscuron.mkin.tileentity.TileProgrammer;

public class GuiProgrammer extends GuiContainer {
    
    private TileProgrammer tileProgrammer;

    public GuiProgrammer(InventoryPlayer inventoryPlayer, TileProgrammer tile) {
        super(new ContainerProgrammer(inventoryPlayer, tile));
        tileProgrammer = tile;
        xSize = 176;
        ySize = 222;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRenderer.drawString(StatCollector.translateToLocal(tileProgrammer.getInvName()), 8, 6, 0x404040);
        fontRenderer.drawString(StatCollector.translateToLocal(ContainerInfo.CONTAINER_INVENTORY), 8, ySize - 96 + 2, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(GuiInfo.PROGRAMMER_GUI_TEXTURE);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

}
