package lj.vgm.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lj.vgm.lib.Reference;
import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidLamp;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVoidLamp extends ContainerVGM {
    
    Icon[] icons;
    
    protected BlockVoidLamp(int par1) {
        super(par1, Material.cloth);
        this.setUnlocalizedName(Strings.VOID_LAMP_UNLOC_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityVoidLamp();
    }
    
    
    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return (world.getBlockMetadata(x, y, z) == 1) ? 14 : 0;
    }
    //TODO Fix this thing
    
    public void registerIcons(IconRegister iconRegister) {
        icons = new Icon[2];
        for (int i = 0; i < 2; i++) {
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()
                    + ":" + Strings.VOID_LAMP_TEXTURE_NAMES[i]);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int id, int meta) {
        if (meta < 2)
            return icons[meta];
        else return icons[0];
    }
    
    public static void updateLampBlockState(boolean isLit, World worldObj, int xCoord, int yCoord, int zCoord)
    {
        TileEntity tileentity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
        //keepFurnaceInventory = true;

        if (isLit)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
        else
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);

        //keepFurnaceInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            worldObj.setBlockTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }
}
