package lj.vgm.render;

import lj.vgm.core.util.ExtendedItemStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        // Use vanilla code to render the icon in a 16x16 square of inventory
        // slot
        renderItem.renderIcon(0, 0, itemIcon, 16, 16);

        NBTTagCompound nbt = item.getTagCompound();
        String text = "0";
        if (nbt != null) {
            ExtendedItemStack capsuleStack = ExtendedItemStack.loadExtendedItemStackFromNBT(nbt);
            if (capsuleStack != null && capsuleStack.isValid()) {
                int tier = capsuleStack.stackSize;
                tier = (int) Math.floor(Math.log(tier)/Math.log(2));
                tier = Math.max(0, tier - 5);
                text = Integer.toString(tier);
            }
        }
        fontRenderer.drawStringWithShadow(text, 6, 4, 0xFFFF00);
    }
}
