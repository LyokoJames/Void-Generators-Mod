package lj.vgm.render;

import lj.vgm.lib.RenderIds;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class VoidConduitRenderer implements
        ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID,
            RenderBlocks renderer) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
            Block block, int modelId, RenderBlocks renderer) {
        
        //TODO get info from Tile Entity
        
        //Render Center Block
        renderer.overrideBlockTexture = block.getIcon(0, 0);
        renderer.setRenderBounds(5d/16d, 5d/16d, 5d/16d,
                11d/16d, 11d/16d, 11d/16d);
        renderer.renderStandardBlock(block, x, y, z);
        
        //Render Down Conduit
        renderer.overrideBlockTexture = block.getIcon(0, 2);
        renderer.setRenderBounds(5d/16d, 0d, 5d/16d,
                11d/16d, 5d/16d, 11d/16d);
        renderer.renderStandardBlock(block, x, y, z);
        
        //Render Top Conduit
        renderer.overrideBlockTexture = block.getIcon(0, 1);
        renderer.setRenderBounds(5d/16d, 11d/16d, 5d/16d,
                11d/16d, 1d, 11d/16d);
        renderer.renderStandardBlock(block, x, y, z);
        
        //Render North Conduit
        renderer.setRenderBounds(5d/16d, 5d/16d, 0d,
                11d/16d, 11d/16d, 5d/16d);
        //Render East Seperately
        renderer.overrideBlockTexture = block.getIcon(0, 4);
        renderer.renderFaceXPos(block, x, y, z, block.getIcon(0, 0));
        //Render Rest of Faces
        renderer.overrideBlockTexture = block.getIcon(0, 2);
        renderer.renderFaceYPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceYNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZNeg(block, x, y, z, block.getIcon(0, 0));
        
        //Render South Face;
        renderer.overrideBlockTexture = block.getIcon(0, 2);
        renderer.setRenderBounds(5d/16d, 5d/16d, 11d/16d,
                11d/16d, 11d/16d, 1d);
        //Render East Seperately
        renderer.overrideBlockTexture = block.getIcon(0, 4);
        renderer.renderFaceXPos(block, x, y, z, block.getIcon(0, 0));
        //Render Rest of Faces
        renderer.overrideBlockTexture = block.getIcon(0, 2);
        renderer.renderFaceYPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceYNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZNeg(block, x, y, z, block.getIcon(0, 0));
        
        //Render West Face
        renderer.overrideBlockTexture = block.getIcon(0, 2);
        renderer.setRenderBounds(0d, 5d/16d, 5d/16d,
                5d/16d, 11d/16d, 11d/16d);
        //Render North Seperately
        renderer.overrideBlockTexture = block.getIcon(0, 4);
        renderer.renderFaceZNeg(block, x, y, z, block.getIcon(0, 0));
        //Render Rest of Faces
        renderer.overrideBlockTexture = block.getIcon(0, 2);
        renderer.renderFaceYPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceZPos(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceYNeg(block, x, y, z, block.getIcon(0, 0));
        renderer.renderFaceXPos(block, x, y, z, block.getIcon(0, 0));
        
        //Render East Face
        renderer.overrideBlockTexture = block.getIcon(0, 2);
        renderer.setRenderBounds(11d/16d, 5d/16d, 5d/16d,
                1d, 11d/16, 11d/16d);
        renderer.renderStandardBlock(block, x, y, z);
        
        renderer.clearOverrideBlockTexture();
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getRenderId() {
        // TODO Auto-generated method stub
        return RenderIds.VOID_CONDUIT;
    }

}
