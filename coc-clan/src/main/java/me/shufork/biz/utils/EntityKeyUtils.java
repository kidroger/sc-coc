package me.shufork.biz.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class EntityKeyUtils {

    private static final String KEY_JOIN_CHAR = "-";

    private EntityKeyUtils(){}

    private static String makeKey(List<String> strings){
        return strings.stream()
                .map(o-> StringUtils.trimToNull(o.toLowerCase()))
                .filter(o-> !StringUtils.isBlank(o))
                .collect(Collectors.joining(KEY_JOIN_CHAR));
    }

    public static String keyOfWarLog(String clanTag,String endTime){
        if(StringUtils.isBlank(clanTag) || StringUtils.isBlank(endTime)){
            throw new IllegalStateException("Can not make key");
        }
        return makeKey(Arrays.asList(
                clanTag,
                endTime
                )
        );
    }
}
