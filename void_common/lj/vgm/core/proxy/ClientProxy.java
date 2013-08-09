package lj.vgm.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import lj.vgm.block.ModBlocks;
import lj.vgm.lib.ItemIds;
import lj.vgm.lib.RenderIds;
import lj.vgm.render.ItemCapsuleRenderer;
import lj.vgm.render.TileEntityVoidConduitRenderer;
import lj.vgm.tileentity.TileEntityVoidConduit;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
    @Override
    public void initRenderersAndTextures() {
        MinecraftForgeClient.registerItemRenderer(
                ItemIds.ITEM_CAPSULE, new ItemCapsuleRenderer());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidConduit.class,
                new TileEntityVoidConduitRenderer());
    }
}
