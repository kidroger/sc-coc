package me.shufork.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class KeyBuilder {
    private static final String KEY_JOIN_CHAR = "-";

    private KeyBuilder(){}

    public static String makeKey(List<String> strings){
        return strings.stream()
                .map(o-> StringUtils.trimToNull(o.toLowerCase()))
                .filter(o-> !StringUtils.isBlank(o))
                .collect(Collectors.joining(KEY_JOIN_CHAR));
    }
}
