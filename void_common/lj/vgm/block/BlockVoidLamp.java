package lj.vgm.block;

import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidLamp;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVoidLamp extends ContainerVGM {

    protected BlockVoidLamp(int par1) {
        super(par1, Material.cloth);
        this.setUnlocalizedName(Strings.VOID_LAMP_UNLOC_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityVoidLamp();
    }
    
    /*@Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        TileEntityVoidLamp te = (TileEntityVoidLamp) world.getBlockTileEntity(x, y, z);
        if (te != null)
            return te.on ? 15 : 0;
        else return 0;
    }

}
    }*/
    //TODO Fix this thing

}
