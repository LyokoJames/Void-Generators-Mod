package lj.vgm.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import lj.vgm.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipes {
    public static void init() {
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemCapsule, 1),
                new Object[] { "D",
                               'D', Block.dirt });
        }
}
