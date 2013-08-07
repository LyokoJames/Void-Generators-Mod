package lj.vgm;

import net.minecraft.creativetab.CreativeTabs;
import lj.vgm.core.proxy.CommonProxy;
import lj.vgm.creativetab.CreativeTabVGM;
import lj.vgm.item.ModItems;
import lj.vgm.lib.Reference;
import lj.vgm.network.PacketHandler;
import lj.vgm.recipes.CraftingRecipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class VoidGenerators {
    
    @Instance(Reference.MOD_ID)
    public static VoidGenerators instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
    
    public static CreativeTabs tabsVGM = new CreativeTabVGM(CreativeTabs.getNextID(), Reference.MOD_ID);
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
    }
    
    @Init
    public void init(FMLInitializationEvent event) {
        CraftingRecipes.init();
        //proxy.initRenderersAndTextures();
    }
}
