package me.shufork.biz.utils;

import me.shufork.common.utils.KeyBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public final class EntityKeyUtils {
    private EntityKeyUtils(){}
    public static String keyOfWarLog(String clanTag,String endTime){
        if(StringUtils.isBlank(clanTag) || StringUtils.isBlank(endTime)){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey(Arrays.asList(
                clanTag,
                endTime
                )
        );
    }
}
