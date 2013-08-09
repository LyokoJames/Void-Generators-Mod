package lj.vgm.block;

import lj.vgm.VoidGenerators;
import lj.vgm.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ContainerVGM extends BlockContainer {

    protected ContainerVGM(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setCreativeTab(VoidGenerators.tabsVGM);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName2());
    }

}
