package lj.vgm.core.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import lj.vgm.lib.ItemIds;
import lj.vgm.lib.RenderIds;
import lj.vgm.render.ItemCapsuleRenderer;
import lj.vgm.render.VoidConduitRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
    @Override
    public void initRenderersAndTextures() {
        MinecraftForgeClient.registerItemRenderer(
                ItemIds.ITEM_CAPSULE, new ItemCapsuleRenderer());
        
        RenderIds.VOID_CONDUIT = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(RenderIds.VOID_CONDUIT, new VoidConduitRenderer());
    }
}
