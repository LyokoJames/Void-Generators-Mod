package lj.vgm.tileentity;

import lj.vgm.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityVoidConduit.class, Strings.VOID_CONDUIT_TILE_ENTITY);
        GameRegistry.registerTileEntity(TileEntityVoidCollector.class, Strings.VOID_COLLECTOR_TILE_ENTITY);
        GameRegistry.registerTileEntity(TileEntityVoidLamp.class, Strings.VOID_LAMP_TILE_ENTITY);
    }
}
