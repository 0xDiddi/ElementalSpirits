package theultimatehose.elementalspirits.util;

import net.minecraftforge.fml.common.Mod;

public class StringUtil {

    public static String getUnlocalizedChat(String identifier) {
        return "chat." + ModUtil.MOD_ID_LOWER + '.' + identifier + ".msg";
    }

}
