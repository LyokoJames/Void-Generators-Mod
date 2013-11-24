package lj.vgm.core.proxy;

import lj.vgm.client.gui.inventory.GuiVoidFurnace;
import lj.vgm.inventory.ContainerVoidFurnace;
import lj.vgm.lib.GuiIds;
import lj.vgm.tileentity.TileEntityVoidFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
    public void initRenderersAndTextures() {
        
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        if (ID == GuiIds.VOID_FURNACE) {
            TileEntityVoidFurnace voidFurnaceTe = (TileEntityVoidFurnace) world.getBlockTileEntity(x, y, z);
            return new ContainerVoidFurnace(player.inventory, voidFurnaceTe);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        if (ID == GuiIds.VOID_FURNACE) {
            TileEntityVoidFurnace voidFurnaceTe = (TileEntityVoidFurnace) world.getBlockTileEntity(x, y, z);
            return new GuiVoidFurnace(player.inventory, voidFurnaceTe);
        }
        return null;
    }
}
