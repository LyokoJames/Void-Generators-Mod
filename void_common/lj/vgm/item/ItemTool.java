package lj.vgm.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidConduit;

public class ItemTool extends ItemVGM {

    public ItemTool(int id) {
        super(id);
        this.setUnlocalizedName(Strings.ITEM_TOOL_UNLOC_NAME);
    }
    
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        if (!world.isRemote) {
        System.out.println("Used from: " + (world.isRemote ? "Client" : "Server"));
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null) {
            if (te instanceof TileEntityVoidConduit) {
                if (par2EntityPlayer.isSneaking())
                    ((TileEntityVoidConduit) te).flipStates();
                else ((TileEntityVoidConduit) te).conduits[side].cycleState();
                ((TileEntityVoidConduit) te).serverSyncToClient();
            }
        }
        }
        return true;
    }

}
