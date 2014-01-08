package lj.vgm.block;

import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVoidGenerator extends ContainerVGM {

    protected BlockVoidGenerator(int par1) {
        super(par1, Material.rock);
        this.setUnlocalizedName(Strings.VOID_GENERATOR_UNLOC_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityVoidGenerator();
    }

}
