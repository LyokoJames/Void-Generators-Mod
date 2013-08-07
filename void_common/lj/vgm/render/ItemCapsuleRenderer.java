package lj.vgm.render;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

public class ItemCapsuleRenderer implements IItemRenderer {

    private static RenderItem renderItem = new RenderItem();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.INVENTORY;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        // Get icon index for the texture
        Icon itemIcon = item.getIconIndex();
        // TODO FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        // Use vanilla code to render the icon in a 16x16 square of inventory
        // slot
        renderItem.renderIcon(0, 0, itemIcon, 16, 16);
        
        //TODO Insert Tier Finidng and Rendering Code Here
    }

}
