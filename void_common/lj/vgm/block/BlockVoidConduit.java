package lj.vgm.block;

import lj.vgm.core.util.ConduitState;
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
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack) {

        ForgeDirection direction = null;
        int pitch = MathHelper.floor_double(entityLiving.rotationPitch * 6.0F / 180.0F + 0.5D);
        int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        if(pitch <= -2) direction = ForgeDirection.UP;
        else if(pitch >= 2) direction = ForgeDirection.DOWN;
        else {
        if (facing == 0) {
            direction = ForgeDirection.SOUTH;
        }
        else if (facing == 1) {
            direction = ForgeDirection.WEST;
        }
        else if (facing == 2) {
            direction = ForgeDirection.NORTH;
        }
        else if (facing == 3) {
            direction = ForgeDirection.EAST;
        }
        }
        
        
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
