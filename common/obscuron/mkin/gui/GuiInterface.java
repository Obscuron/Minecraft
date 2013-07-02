package obscuron.mkin.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import obscuron.mkin.container.ContainerInterface;
import obscuron.mkin.lib.ContainerInfo;
import obscuron.mkin.lib.GuiInfo;
import obscuron.mkin.tileentity.TileInterface;

import org.lwjgl.opengl.GL11;

public class GuiInterface extends GuiContainer {

    private TileInterface tileInterface;
    
    private int xStart;
    private int yStart;
    
    @Override
    public void initGui() {
        super.initGui();
        xStart = (width - xSize) / 2;
        yStart = (height - ySize) / 2;
    }

    public GuiInterface(InventoryPlayer inventoryPlayer, TileInterface tile) {
        super(new ContainerInterface(inventoryPlayer, tile));
        tileInterface = tile;
        xSize = 176;
        ySize = 166;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRenderer.drawString(StatCollector.translateToLocal(tileInterface.getInvName()), 8, 6, 0x404040);
        fontRenderer.drawString(StatCollector.translateToLocal(ContainerInfo.CONTAINER_INVENTORY), 8, ySize - 96 + 2, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(GuiInfo.INTERFACE_GUI_TEXTURE);
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

}
