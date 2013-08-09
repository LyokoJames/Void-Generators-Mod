package lj.vgm.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lj.vgm.VoidGenerators;
import lj.vgm.lib.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemVGM extends Item {

    public ItemVGM(int id) {
        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
        this.setCreativeTab(VoidGenerators.tabsVGM);
        maxStackSize = 1;
        setNoRepair();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()
                + ":"
                + this.getUnlocalizedName().substring(
                        this.getUnlocalizedName().indexOf(".") + 1));
    }

}
