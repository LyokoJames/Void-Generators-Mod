package lj.vgm.render;

import org.lwjgl.opengl.GL11;

import lj.vgm.lib.Reference;
import lj.vgm.model.VoidConduitModel;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVoidConduitRenderer extends TileEntityRendererVGM {
    
    private VoidConduitModel model;
    
    public TileEntityVoidConduitRenderer() {
        model = new VoidConduitModel();
    }

    @Override
    public void renderBlock(TileEntity te, double x, double y, double z, float scale) {
        
        GL11.glPushMatrix();
        
        renderModel(true, false, false, false, false, false);
        GL11.glPopMatrix();
        
    }
    
    @Override
    public String getTextureDir(TileEntity te) {
        return Reference.BLOCK_TEXTURES_DIRECTORY + "VoidConduitSingle.png";
    }
    
    private void renderModel(boolean base, boolean top, boolean front, boolean back, boolean right, boolean left) {
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                base, top, front, back, right, left);
    }
}
