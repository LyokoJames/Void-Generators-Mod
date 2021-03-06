package lj.vgm.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class TileEntityRendererVGM extends TileEntitySpecialRenderer {

    @SuppressWarnings("unused")
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
}

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        //This is the texture of your block. It's pathed to be the same place as your other blocks here.
        bindTextures(te);
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        renderBlock(te, x, y, z, scale);
        
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
    
    public void bindTextures(TileEntity te) {
        bindTextureByName(getTextureDir(te));
    }

    public abstract String getTextureDir(TileEntity te);

    public abstract void renderBlock(TileEntity te, double x, double y, double z, float scale);

    //Set the lighting stuff, so it changes it's brightness properly.       
    @SuppressWarnings("unused")
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        Tessellator tess = Tessellator.instance;
        float brightness = block.getBlockBrightness(world, i, j, k);
        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65536;
        tess.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  (float) modulousModifier,  divModifier);
    }

}
