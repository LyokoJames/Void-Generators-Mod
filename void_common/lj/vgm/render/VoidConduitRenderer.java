package lj.vgm.render;

import lj.vgm.lib.RenderIds;
import lj.vgm.tileentity.TileEntityVoidConduit;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
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
        
        //TODO insert brightness correction methods
        
        TileEntityVoidConduit te = (TileEntityVoidConduit) world.getBlockTileEntity(x, y, z);
            te = (TileEntityVoidConduit) world.getBlockTileEntity(x, y, z);
            if (!te.initialSync) te.clientRequestServerSync();
        
        //Render Center Block
        renderer.overrideBlockTexture = block.getIcon(0, 0);
        renderer.setRenderBounds(5d/16d, 5d/16d, 5d/16d,
                11d/16d, 11d/16d, 11d/16d);
        renderer.renderStandardBlock(block, x, y, z);
        
        if (te.conduits[0].state.isConnected()) {
        //Render Down Conduit
        renderer.overrideBlockTexture = te.conduits[0].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.setRenderBounds(5d/16d, 0d, 5d/16d,
                11d/16d, 5d/16d, 11d/16d);
        renderer.renderStandardBlock(block, x, y, z);
        }
        
        if (te.conduits[1].state.isConnected()) {
        //Render Top Conduit
        renderer.overrideBlockTexture = te.conduits[1].state.isInput() ? 
                block.getIcon(0, 1) : block.getIcon(0, 2);
        renderer.setRenderBounds(5d/16d, 11d/16d, 5d/16d,
                11d/16d, 1d, 11d/16d);
        renderer.renderStandardBlock(block, x, y, z);
        }
        
        if (te.conduits[2].state.isConnected()) {
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
        renderer.clearOverrideBlockTexture();
        return true;
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
