package lj.vgm.render;

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
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    }
    
    @Override
    public void bindTextures(TileEntity te) {
       bindTextureByName("/mods/vgm/textures/blocks/VoidConduitSingle.png");
    }
}
