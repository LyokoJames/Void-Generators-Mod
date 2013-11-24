package lj.vgm.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import lj.vgm.lib.BlockIds;
import lj.vgm.lib.Strings;
import net.minecraft.block.Block;

public class ModBlocks {
    
    public static Block voidEnergyConduit;
    public static Block voidEnergyCollector;
    public static Block voidEnergyLamp;
    public static Block voidFurnace;

    public static void init() {
        voidEnergyConduit = new BlockVoidConduit(BlockIds.VOID_CONDUIT);
        voidEnergyCollector = new BlockVoidCollector(BlockIds.VOID_COLLECTOR);
        voidEnergyLamp = new BlockVoidLamp(BlockIds.VOID_LAMP);
        voidFurnace = new BlockVoidFurnace(BlockIds.VOID_FURNACE);
        
        GameRegistry.registerBlock(voidEnergyConduit, Strings.VOID_CONDUIT_UNLOC_NAME);
        GameRegistry.registerBlock(voidEnergyCollector, Strings.VOID_COLLECTOR_UNLOC_NAME);
        GameRegistry.registerBlock(voidEnergyLamp, Strings.VOID_LAMP_UNLOC_NAME);
        GameRegistry.registerBlock(voidFurnace, Strings.VOID_FURNACE_UNLOC_NAME);
        
        LanguageRegistry.addName(voidEnergyCollector, "VEC");
        LanguageRegistry.addName(voidEnergyLamp, "VEL");
        LanguageRegistry.addName(voidFurnace, "Void Energy Furnace");
    }

}
