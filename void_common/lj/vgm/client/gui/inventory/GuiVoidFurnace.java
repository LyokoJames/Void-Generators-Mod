package lj.vgm.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import lj.vgm.inventory.ContainerVoidFurnace;
import lj.vgm.lib.Textures;
import lj.vgm.tileentity.TileEntityVoidFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiVoidFurnace extends GuiContainer {
    
    //TODO Change Void Furnace GUI code and Texture to remove fuel slot
    
    private TileEntityVoidFurnace voidFurnaceTE;
    
    public GuiVoidFurnace(InventoryPlayer inventoryPlayer, TileEntityVoidFurnace voidFurnace) {
        super(new ContainerVoidFurnace(inventoryPlayer, voidFurnace));
        voidFurnaceTE = voidFurnace;
        ySize = 176;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        //TODO Foreground Layer by copying from regular Furnace and/or pahimar
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(Textures.GUI_VOID_FURNACE);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int i1;
        
        i1 = (int) Math.round(((double)this.voidFurnaceTE.voidEnergy / (double) this.voidFurnaceTE.maxVoidEnergy * 52));
        
        this.drawTexturedModalRect(xStart + 18,yStart + 69 - i1, 176, 84 - i1, 16, i1);
        
        if (voidFurnaceTE.isBurning())
        {
            i1 = voidFurnaceTE.currentBurnTime * 12 / TileEntityVoidFurnace.TOTAL_BURN_TIME;
            this.drawTexturedModalRect(xStart + 56, yStart + 36 + 12 - i1, 176,
                    12 - i1, 14, i1 + 2);
        }
        
        i1 = voidFurnaceTE.currentCookTime * 24 / TileEntityVoidFurnace.TOTAL_COOK_TIME;
        this.drawTexturedModalRect(xStart + 79, yStart + 34, 176, 14, i1 + 1, 16);
        
        
    }

}
