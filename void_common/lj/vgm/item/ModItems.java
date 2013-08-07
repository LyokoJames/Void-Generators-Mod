package lj.vgm.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import lj.vgm.lib.ItemIds;
import lj.vgm.lib.Strings;

public class ModItems {
    
    public static Item itemCapsule;
    
    public static void init() {
        itemCapsule = new ItemItemCapsule(ItemIds.ITEM_CAPSULE);
        
        LanguageRegistry.addName(itemCapsule, Strings.ITEM_CAPSULE_UNLOC_NAME);
    }
}
