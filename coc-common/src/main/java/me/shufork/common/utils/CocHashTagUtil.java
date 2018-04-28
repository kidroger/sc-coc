package me.shufork.common.utils;

import me.shufork.common.constants.CocConstants;
import org.apache.commons.lang3.StringUtils;

public final class CocHashTagUtil {

    private CocHashTagUtil(){}

    public static String ensurePrefix(String val){
        return StringUtils.prependIfMissing(val, CocConstants.TAG_PREFIX);
    }
    public static boolean hasPrefix(String val){return !StringUtils.isBlank(val) && val.charAt(0) == CocConstants.TAG_CHAR;}
}
