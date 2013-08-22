package lj.vgm.block;

import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidCollector;
import lj.vgm.tileentity.TileEntityVoidConduit;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVoidCollector extends ContainerVGM {

    public BlockVoidCollector(int par1) {
        super(par1, Material.cloth);
        this.setUnlocalizedName(Strings.VOID_CONDUIT_UNLOC_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityVoidCollector();
    }

}
