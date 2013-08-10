package lj.vgm.block;

import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidConduit;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockVoidConduit extends ContainerVGM {

    protected BlockVoidConduit(int par1) {
        super(par1, Material.cloth);
        this.setUnlocalizedName(Strings.VOID_CONDUIT_UNLOC_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityVoidConduit();
    }
    
  //This will tell minecraft not to render any side of our cube.
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
       return false;
    }

    //And this tell it that you can see through this block, and neighbor blocks should be rendered.
    public boolean isOpaqueCube()
    {
       return false;
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack) {

        ForgeDirection direction = null;
        int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (facing == 0) {
            direction = ForgeDirection.NORTH;
        }
        else if (facing == 1) {
            direction = ForgeDirection.EAST;
        }
        else if (facing == 2) {
            direction = ForgeDirection.SOUTH;
        }
        else if (facing == 3) {
            direction = ForgeDirection.WEST;
        }
        
        
        TileEntityVoidConduit te = (TileEntityVoidConduit) world.getBlockTileEntity(x, y, z);
        te.setConduitDirections(direction);
        te.setDirectionConnection(direction.getOpposite(), true);
        
    }

}
