package lj.vgm.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import lj.vgm.inventory.ContainerVoidFurnace;
import lj.vgm.lib.Textures;
import lj.vgm.tileentity.TileEntityVoidFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiVoidFurnace extends GuiContainer {

    private TileEntityVoidFurnace voidFurnaceTE;
    
    public GuiVoidFurnace(InventoryPlayer inventoryPlayer, TileEntityVoidFurnace voidFurnace) {
        super(new ContainerVoidFurnace(inventoryPlayer, voidFurnace));
        voidFurnaceTE = voidFurnace;
        ySize = 176;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        //TODO stuff
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(Textures.GUI_VOID_FURNACE);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

}
