package lj.vgm.lib;

import java.io.File;

import lj.vgm.core.util.FileHelper;

public class Reference {
    public static final String MOD_ID = "vgm";
    public static final String MOD_NAME = "Void Generators";
    public static final String VERSION = "0.0.1";
    public static final String CHANNEL_NAME = MOD_ID;
    
    
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
    
    public static final String DEV_TOKEN = "@DEV_TOKEN";
    @SuppressWarnings("unused")
    public static final boolean IS_IN_A_JAR = DEV_TOKEN == "yes" ? true : false;
        
    public static final String CLIENT_PROXY_CLASS = "lj.vgm.core.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "lj.vgm.core.proxy.CommonProxy";
    
    public static final String MOD_RESOURCES_DIRECTORY =
            FileHelper.getModDirectory()
            + "mods" + File.separator + Reference.MOD_ID + File.separator;
    public static final String BLOCK_TEXTURES_DIRECTORY =
            MOD_RESOURCES_DIRECTORY + "textures" + File.separator
            + "blocks" + File.separator;
}
