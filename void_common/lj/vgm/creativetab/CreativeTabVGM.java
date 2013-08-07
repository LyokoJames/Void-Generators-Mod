package lj.vgm.creativetab;

import lj.vgm.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabVGM extends CreativeTabs {
    
    public CreativeTabVGM(int par1, String par2Str) {
        super(par1, par2Str);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {

        return Item.redstone.itemID;
    }

    @Override
    public String getTranslatedTabLabel() {
        return Reference.MOD_NAME;
    }
}
