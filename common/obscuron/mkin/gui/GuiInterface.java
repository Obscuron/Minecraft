package obscuron.mkin.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import obscuron.mkin.container.ContainerInterface;
import obscuron.mkin.lib.ContainerInfo;
import obscuron.mkin.lib.GuiInfo;
import obscuron.mkin.network.packet.KineticPacket;
import obscuron.mkin.network.packet.PacketProgrammer;
import obscuron.mkin.tileentity.TileInterface;

import org.lwjgl.opengl.GL11;

public class GuiInterface extends GuiContainer {

    private TileInterface tileInterface;
    
    private int xStart;
    private int yStart;
    
    private String[] text = {"button0", "button1", "button2"};
    private int index = 0;
    
    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        xStart = (width - xSize) / 2;
        yStart = (height - ySize) / 2;
        buttonList.add(new GuiButton(0, xStart + 8, yStart + 30, 100, 20, text[index]));
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
    
    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            if (index == 0) {
                ItemStack itemStack = tileInterface.getStackInSlot(0);
                if (itemStack != null) {
                    int size = 2 * itemStack.stackSize;
                    if (size > itemStack.getItem().getItemStackLimit()) {
                        size = itemStack.getItem().getItemStackLimit();
                    }
                    itemStack.stackSize = size;
                    tileInterface.onInventoryChanged();
                }
                PacketProgrammer packet = new PacketProgrammer();
                packet.readInfo(tileInterface);
                packet.sendPacket();
            }
            index++;
            if (index >= text.length) {
                index = 0;
            }
            buttonList.clear();
            buttonList.add(new GuiButton(0, xStart + 8, yStart + 30, 100, 20, text[index]));
        }
    }

}
