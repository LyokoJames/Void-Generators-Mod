package lj.vgm.block;

import lj.vgm.core.util.ConduitState;
import lj.vgm.core.util.DirectionHelper;
import lj.vgm.lib.Reference;
import lj.vgm.lib.RenderIds;
import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidConduit;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockVoidConduit extends ContainerVGM {
    
    Icon[] icons;

    protected BlockVoidConduit(int par1) {
        super(par1, Material.cloth);
        this.setUnlocalizedName(Strings.VOID_CONDUIT_UNLOC_NAME);
        //this.setBlockBounds(4f/16f, 4f/16f, 4f/16f, 12f/16f, 12f/16f, 12f/16f);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityVoidConduit();
    }

    //And this tell it that you can see through this block, and neighbor blocks should be rendered.
    public boolean isOpaqueCube()
    {
       return false;
    }
    
    /*public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        float minX = 5f/16f;
        float minY = 5f/16f;
        float minZ = 5f/16f;
        float maxX = 11f/16f;
        float maxY = 11f/16f;
        float maxZ = 11f/16f;
        
        TileEntityVoidConduit teVc = (TileEntityVoidConduit) world.getBlockTileEntity(x, x, z);
        if (teVc != null) {
            minY -= teVc.conduits[0].state.isConnected() ? 5f/16f : 0f;
            maxY += teVc.conduits[1].state.isConnected() ? 5f/16f : 0f;
            minZ -= teVc.conduits[2].state.isConnected() ? 5f/16f : 0f;
            maxZ += teVc.conduits[3].state.isConnected() ? 5f/16f : 0f;
            minX -= teVc.conduits[4].state.isConnected() ? 5f/16f : 0f;
            maxX += teVc.conduits[5].state.isConnected() ? 5f/16f : 0f;
        }
        
        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }*/
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack) {

        ForgeDirection direction = DirectionHelper.getEntityLookDirection(entityLiving);
        
        TileEntityVoidConduit te = (TileEntityVoidConduit) world.getBlockTileEntity(x, y, z);
        te.determineConnections(direction);
    }
    
    @Override
    public int getRenderType() {
        return RenderIds.VOID_CONDUIT;
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        
        icons = new Icon[6];
        
        for (int i = 0; i < 5; i++) {
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()
                    + ":"
                    + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1)
                    + Strings.VOID_CONDUIT_TEXTURE_NAMES[i]);
        }
    }
    
    @Override
    public Icon getIcon(int side, int meta) {
        return icons[meta];
    }

}
