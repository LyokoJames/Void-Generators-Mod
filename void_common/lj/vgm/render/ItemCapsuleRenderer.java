package lj.vgm.render;

import lg.vgm.nbt.NBTHelper;
import lj.vgm.item.ExtendedItemStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

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
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        // Use vanilla code to render the icon in a 16x16 square of inventory
        // slot
        renderItem.renderIcon(0, 0, itemIcon, 16, 16);
        
        ExtendedItemStack capsuleStack = NBTHelper.NBTToExtendedStack(
                item.getTagCompound(), "");
        if (capsuleStack != null) {
            if (capsuleStack.hasValidItemStack()) {
                String text = Integer.toString(capsuleStack.getTier());
                fontRenderer.drawStringWithShadow(text, 1, 1, 0xFFFFFF);
            }
        }
    }

}
