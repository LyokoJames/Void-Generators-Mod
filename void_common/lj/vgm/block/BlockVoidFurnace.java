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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
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
                    player.openGui(VoidGenerators.instance, GuiIds.GUI_VOID_FURNACE, world, x, y, z);
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
        if (meta == 0 & side == 3)return icons[3];
        else if(side == 1 || side == 0) return icons[0];
        else if (side == meta) return icons[3];
        else if (meta > 5 && side + 4 == meta) return icons[2];
        else return icons[1];
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack) {

        int direction = 0;
        int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (facing == 0) {
            direction = ForgeDirection.NORTH.ordinal();
        }
        else if (facing == 1) {
            direction = ForgeDirection.EAST.ordinal();
        }
        else if (facing == 2) {
            direction = ForgeDirection.SOUTH.ordinal();
        }
        else if (facing == 3) {
            direction = ForgeDirection.WEST.ordinal();
        }

        world.setBlockMetadataWithNotify(x, y, z, direction, 3);
        //TODO Replace with Direction Helper
    }
    
    public static void updateFurnaceBlockState(boolean isBurning, World worldObj, int xCoord, int yCoord, int zCoord)
    {
        int currentMeta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileentity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
        //keepFurnaceInventory = true;

        if (isBurning && currentMeta < 6)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord,
                    currentMeta + 4, 3);
        else if (isBurning && currentMeta > 5)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord,
                    currentMeta - 4, 3);

        //keepFurnaceInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            worldObj.setBlockTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

}
