package lj.vgm.core.proxy;

import lj.vgm.lib.ItemIds;
import lj.vgm.render.ItemCapsuleRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
    @Override
    public void initRenderersAndTextures() {
        MinecraftForgeClient.registerItemRenderer(
                ItemIds.ITEM_CAPSULE, new ItemCapsuleRenderer());
    }
}
