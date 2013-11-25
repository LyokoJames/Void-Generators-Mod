package lj.vgm.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lj.vgm.VoidGenerators;
import lj.vgm.lib.GuiIds;
import lj.vgm.lib.Reference;
import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockVoidFurnace extends ContainerVGM {

    Icon[] icons;
    
    protected BlockVoidFurnace(int par1) {
        super(par1, Material.rock);
        this.setUnlocalizedName(Strings.VOID_FURNACE_UNLOC_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityVoidFurnace();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (player.isSneaking())
            return true;
        else if (world.isBlockSolidOnSide(x, y + 1, z, ForgeDirection.DOWN))
            return true;
        else {
            if (!world.isRemote) {
                TileEntity te = world.getBlockTileEntity(x, y, z);

                if (te != null) {
                    if (te instanceof TileEntityVoidFurnace)
                    player.openGui(VoidGenerators.instance, GuiIds.VOID_FURNACE, world, x, y, z);
                }
            }

            return true;
        }
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        
        icons = new Icon[4];
        
        for (int i = 0; i < 4; i++) {
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()
                    + ":" +
                    Strings.VOID_FURNACE_TEXTURE_NAMES[i]);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        if(side == 0) return icons[0];
        else if (side == 2) return icons[3];
        else return icons[1];
    }

}
