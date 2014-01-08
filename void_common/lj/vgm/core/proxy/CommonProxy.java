package lj.vgm.core.proxy;

import lj.vgm.client.gui.inventory.GuiVoidFurnace;
import lj.vgm.inventory.ContainerVoidFurnace;
import lj.vgm.lib.GuiIds;
import lj.vgm.lib.Strings;
import lj.vgm.tileentity.TileEntityVoidCollector;
import lj.vgm.tileentity.TileEntityVoidConduit;
import lj.vgm.tileentity.TileEntityVoidFurnace;
import lj.vgm.tileentity.TileEntityVoidLamp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

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
    
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityVoidConduit.class, Strings.VOID_CONDUIT_TILE_ENTITY);
        GameRegistry.registerTileEntity(TileEntityVoidCollector.class, Strings.VOID_COLLECTOR_TILE_ENTITY);
        GameRegistry.registerTileEntity(TileEntityVoidLamp.class, Strings.VOID_LAMP_TILE_ENTITY);
        GameRegistry.registerTileEntity(TileEntityVoidFurnace.class, Strings.VOID_FURNACE_TILE_ENTITY);
    }
}
