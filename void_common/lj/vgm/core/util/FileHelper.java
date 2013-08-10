package lj.vgm.core.util;

import java.io.File;

import lj.vgm.lib.Reference;

public class FileHelper {
    public static String getModDirectory() {
        if (Reference.IS_IN_A_JAR) {
            return new File("helperFile.txt").getAbsoluteFile()
                    .getAbsolutePath() + File.separator;
        }
        else {
            return new File (new File("helperFile.txt").getAbsoluteFile()
                    .getParentFile().getParentFile().getParentFile(),
                    "VoidGenerators" + File.separator + "resources").
                    getAbsolutePath() + File.separator;
        }
    }
}
