package lj.vgm.render;

import org.lwjgl.opengl.GL11;

import lj.vgm.core.util.ConduitDirection;
import lj.vgm.core.util.ConduitHandler;
import lj.vgm.lib.Reference;
import lj.vgm.model.VoidConduitModel;
import lj.vgm.tileentity.TileEntityVoidConduit;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityVoidConduitRenderer extends TileEntityRendererVGM {
    
    private VoidConduitModel model;
    
    public TileEntityVoidConduitRenderer() {
        model = new VoidConduitModel();
    }

    @Override
    public void renderBlock(TileEntity te, double x, double y, double z, float scale) {
        
        GL11.glPushMatrix();
        
        if(te != null) {
            TileEntityVoidConduit VoidConduit = (TileEntityVoidConduit) te;
            ConduitDirection[] conduits = VoidConduit.conduits.conduits;
            if (conduits[0].actualDir != null) {
                ForgeDirection rotDirection = conduits[0].
                        actualDir.getRotation(ForgeDirection.UP);
                if(rotDirection != null) {
                    if (conduits[0].actualDir != ForgeDirection.DOWN &&
                            conduits[0].actualDir != ForgeDirection.UP) {
                
                        GL11.glTranslatef(0, 1F, 0);
                
                        GL11.glRotatef(-90F, rotDirection.offsetX,
                            0,
                            rotDirection.offsetZ);
                    
                        GL11.glTranslatef(0,-1F,0);
                    }
                    else if (conduits[0].actualDir == ForgeDirection.UP) {
                        GL11.glRotatef(180F,0,0,1F);
                        GL11.glTranslatef(0,-2F,0);
                    }
                }
                
            }
        
            this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    conduits);
        }
        else
            this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    ConduitHandler.getDefaultConduits());
        GL11.glPopMatrix();
        
    }
    
    @Override
    public String getTextureDir(TileEntity te) {
        return Reference.BLOCK_TEXTURES_DIRECTORY + "VoidConduitSingle.png";
    }
}
