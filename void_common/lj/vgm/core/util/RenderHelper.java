package lj.vgm.core.util;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

public class RenderHelper {
    
    //TODO Maybe move class to lj.vgm.render
    
    private final int x;
    private final int y;
    private final int z;
    private final int l;
    private final Block block;
    private final Tessellator tessellator;
    private final RenderBlocks renderer;
    
    public RenderHelper(int _x, int _y, int _z, Tessellator tess, RenderBlocks rend, Block blk) {
        x = _x;
        y = _y;
        z = _z;
        tessellator = tess;
        renderer = rend;
        block = blk;
        l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
    }
    
    /*Side Multipliers:
     *YNeg = 0.5F
     *YPos = 1F
     *ZNeg = 0.8F
     *ZPos = 0.8F
     *XNeg = 0.6F
     *XPos = 0.6F
     */
    
    //Methods for helping render custom blocks.
    
    public void renderYNegColorMultiplier() {
        tessellator.setBrightness(renderer.renderMinY > 0.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z));
        tessellator.setColorOpaque_F(0.5F, 0.5F, 0.5F);
        renderer.renderFaceYNeg(block, (double)x, (double)y, (double)z, block.getIcon(0, 0));
    }
    
    public void renderYPosColorMultiplier() {
        tessellator.setBrightness(renderer.renderMaxY < 1.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z));
        tessellator.setColorOpaque_F(1F, 1F, 1F);
        renderer.renderFaceYPos(block, (double)x, (double)y, (double)z, block.getIcon(0, 0));
    }
    
    public void renderZNegColorMultiplier() {
        tessellator.setBrightness(renderer.renderMinZ > 0.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1));
        tessellator.setColorOpaque_F(0.8F, 0.8F, 0.8F);
        renderer.renderFaceZNeg(block, (double)x, (double)y, (double)z, block.getIcon(0, 0));
    }
    
    public void renderZPosColorMultiplier() {
        tessellator.setBrightness(renderer.renderMaxZ < 1.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1));
        tessellator.setColorOpaque_F(0.8F, 0.8F, 0.8F);
        renderer.renderFaceZPos(block, (double)x, (double)y, (double)z, block.getIcon(0, 0));
    }
    
    public void renderXNegColorMultiplier() {
        tessellator.setBrightness(renderer.renderMinX > 0.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z));
        tessellator.setColorOpaque_F(0.6F, 0.6F, 0.6F);
        renderer.renderFaceXNeg(block, (double)x, (double)y, (double)z, block.getIcon(0, 0));
    }
    
    public void renderXPosColorMultiplier() {
        tessellator.setBrightness(renderer.renderMaxX < 1.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z));
        tessellator.setColorOpaque_F(0.6F, 0.6F, 0.6F);
        renderer.renderFaceXPos(block, (double)x, (double)y, (double)z, block.getIcon(0, 0));
    }
}
