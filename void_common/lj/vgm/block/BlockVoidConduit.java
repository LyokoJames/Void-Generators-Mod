package lj.vgm.block;

import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidConduit;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

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

}
