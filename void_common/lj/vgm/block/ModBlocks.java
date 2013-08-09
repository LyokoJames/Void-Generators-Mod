package lj.vgm.block;

import cpw.mods.fml.common.registry.GameRegistry;
import lj.vgm.lib.BlockIds;
import lj.vgm.lib.Strings;
import net.minecraft.block.Block;

public class ModBlocks {
    
    public static Block voidEnergyConduit;

    public static void init() {
        voidEnergyConduit = new BlockVoidConduit(BlockIds.VOID_CONDUIT);
        
        GameRegistry.registerBlock(voidEnergyConduit, Strings.VOID_CONDUIT_UNLOC_NAME);
    }

}
