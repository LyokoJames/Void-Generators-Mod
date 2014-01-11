package lj.vgm.render;

import org.lwjgl.opengl.GL11;

import lj.vgm.core.util.RenderHelper;
import lj.vgm.lib.RenderIds;
import lj.vgm.tileentity.TileEntityVoidConduit;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class VoidConduitRenderer implements
        ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID,
            RenderBlocks renderer) {
        //TODO insert brightness correction methods
        
        //TODO make rest of methods
        //TODO replace dead code with some type of randomiser or just choose a nice looking arrangement
        Tessellator tessellator = Tessellator.instance;
        
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        
        //Render Center Block
        renderer.overrideBlockTexture = block.getIcon(0, 0);
        renderer.setRenderBounds(5d/16d, 5d/16d, 5d/16d,
                11d/16d, 11d/16d, 11d/16d);
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        
        //Render Down Conduit
        renderer.overrideBlockTexture = true ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.setRenderBounds(5d/16d, 0d, 5d/16d,
                11d/16d, 5d/16d, 11d/16d);
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        
        //Render Top Conduit
        renderer.overrideBlockTexture = false ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.setRenderBounds(5d/16d, 11d/16d, 5d/16d,
                11d/16d, 1d, 11d/16d);
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        
        /*if (te.conduits[2].state.isConnected()) {
        //Render North Conduit
        renderer.setRenderBounds(5d/16d, 5d/16d, 0d,
                11d/16d, 11d/16d, 5d/16d);
        //Render East Separately
        renderer.overrideBlockTexture = te.conduits[2].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 4);
        renderer.renderFaceXPos(block, x, y, z, block.getIcon(0, 0));
        //Render Rest of Faces
        renderer.overrideBlockTexture = te.conduits[2].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.renderFaceYPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceYNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZNeg(block, x, y, z, block.getIcon(0, 0));
        }
        
        if (te.conduits[3].state.isConnected()) {
        //Render South Face;
        renderer.setRenderBounds(5d/16d, 5d/16d, 11d/16d,
                11d/16d, 11d/16d, 1d);
        //Render East Separately
        renderer.overrideBlockTexture = te.conduits[3].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 4);
        renderer.renderFaceXPos(block, x, y, z, block.getIcon(0, 0));
        //Render Rest of Faces
        renderer.overrideBlockTexture = te.conduits[3].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.renderFaceYPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceYNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZNeg(block, x, y, z, block.getIcon(0, 0));
        }
        
        if (te.conduits[4].state.isConnected()) {
        //Render West Face
        renderer.setRenderBounds(0d, 5d/16d, 5d/16d,
                5d/16d, 11d/16d, 11d/16d);
        //Render North Seperately
        renderer.overrideBlockTexture = te.conduits[4].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 4);
        renderer.renderFaceZNeg(block, x, y, z, block.getIcon(0, 0));
        //Render Rest of Faces
        renderer.overrideBlockTexture = te.conduits[4].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.renderFaceYPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceYNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXPos(block, x, y, z, block.getIcon(0, 0));
        }
        
        if (te.conduits[5].state.isConnected()) {
        //Render East Face
        renderer.setRenderBounds(11d/16d, 5d/16d, 5d/16d,
                1d, 11d/16, 11d/16d);
      //Render North Seperately
        renderer.overrideBlockTexture = te.conduits[5].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 4);
        renderer.renderFaceZNeg(block, x, y, z, block.getIcon(0, 0));
        //Render Rest of Faces
        renderer.overrideBlockTexture = te.conduits[5].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.renderFaceYPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceYNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXPos(block, x, y, z, block.getIcon(0, 0));
        }*/
        
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        
        renderer.clearOverrideBlockTexture();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
            Block block, int modelId, RenderBlocks renderer) {
        
        //TODO Maybe get ambient occlusion working but probably not
        //TODO perhaps add tessellator normal and (start)draw(ingquad) methods to mimic renderInventoryBlock
        
        TileEntityVoidConduit te = (TileEntityVoidConduit) world.getBlockTileEntity(x, y, z);
            te = (TileEntityVoidConduit) world.getBlockTileEntity(x, y, z);
            //if (!te.initialSync) te.clientRequestServerSync();
        
        Tessellator tessellator = Tessellator.instance; //Experimental
        RenderHelper helper = new RenderHelper(x,y,z,tessellator,renderer,block);
            
        //Render Center Block
        renderer.overrideBlockTexture = block.getIcon(0, 0);
        renderer.setRenderBounds(5d/16d, 5d/16d, 5d/16d,
                11d/16d, 11d/16d, 11d/16d);
        renderer.renderStandardBlockWithColorMultiplier(block, x, y, z,1,1,1);
        
        if (te.conduits[0].state.isConnected()) {
        //Render Down Conduit
        renderer.overrideBlockTexture = te.conduits[0].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.setRenderBounds(5d/16d, 0d, 5d/16d,
                11d/16d, 5d/16d, 11d/16d);
        renderer.renderStandardBlockWithColorMultiplier(block, x, y, z,1,1,1);
        }
        
        if (te.conduits[1].state.isConnected()) {
        //Render Top Conduit
        renderer.overrideBlockTexture = te.conduits[1].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.setRenderBounds(5d/16d, 11d/16d, 5d/16d,
                11d/16d, 1d, 11d/16d);
        renderer.renderStandardBlockWithColorMultiplier(block, x, y, z,1,1,1);
        }
        
        if (te.conduits[2].state.isConnected()) {
        //Render North Conduit
        renderer.setRenderBounds(5d/16d, 5d/16d, 0d,
                11d/16d, 11d/16d, 5d/16d);
        //Render East Separately
        renderer.overrideBlockTexture = te.conduits[2].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 4);
        helper.renderXPosColorMultiplier();
                
        //Render Rest of Faces
        renderer.overrideBlockTexture = te.conduits[2].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        
        helper.renderXNegColorMultiplier();
        helper.renderYNegColorMultiplier();
        helper.renderYPosColorMultiplier();
        helper.renderZNegColorMultiplier();
        helper.renderZPosColorMultiplier();
        }
        
        if (te.conduits[3].state.isConnected()) {
        //Render South Face;
        renderer.setRenderBounds(5d/16d, 5d/16d, 11d/16d,
                11d/16d, 11d/16d, 1d);
        //Render East Separately
        renderer.overrideBlockTexture = te.conduits[3].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 4);
        helper.renderXPosColorMultiplier();
        //Render Rest of Faces
        renderer.overrideBlockTexture = te.conduits[3].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        
        helper.renderXNegColorMultiplier();
        helper.renderYNegColorMultiplier();
        helper.renderYPosColorMultiplier();
        helper.renderZNegColorMultiplier();
        helper.renderZPosColorMultiplier();
        }
        
        if (te.conduits[4].state.isConnected()) {
        //Render West Face
        renderer.setRenderBounds(0d, 5d/16d, 5d/16d,
                5d/16d, 11d/16d, 11d/16d);
        //Render North Seperately
        renderer.overrideBlockTexture = te.conduits[4].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 4);
        helper.renderZNegColorMultiplier();
        //Render Rest of Faces
        renderer.overrideBlockTexture = te.conduits[4].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
                
                
        helper.renderXPosColorMultiplier();
        helper.renderXNegColorMultiplier();
        helper.renderYNegColorMultiplier();
        helper.renderYPosColorMultiplier();
        helper.renderZPosColorMultiplier();
        }
        
        if (te.conduits[5].state.isConnected()) {
        //Render East Face
        renderer.setRenderBounds(11d/16d, 5d/16d, 5d/16d,
                1d, 11d/16, 11d/16d);
      //Render North Seperately
        renderer.overrideBlockTexture = te.conduits[5].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 4);
        helper.renderZNegColorMultiplier();
        //Render Rest of Faces
        renderer.overrideBlockTexture = te.conduits[5].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        helper.renderXPosColorMultiplier();
        helper.renderXNegColorMultiplier();
        helper.renderYNegColorMultiplier();
        helper.renderYPosColorMultiplier();
        helper.renderZPosColorMultiplier();
        }
        renderer.clearOverrideBlockTexture();
        return true;
    }

    
    @Override
    public boolean shouldRender3DInInventory() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public int getRenderId() {
        // TODO Auto-generated method stub
        return RenderIds.VOID_CONDUIT;
    }

}
