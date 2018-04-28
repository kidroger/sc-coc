package me.shufork.biz.utils;

import me.shufork.biz.domain.CocWarTeam;
import me.shufork.biz.vo.WarLogEntryClanVo;
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

    public static String keyOf(WarLogEntryClanVo val){
        if(StringUtils.isBlank(val.getTag()) || val.getWarTime() == null ){
            throw new IllegalStateException("Can not make key");
        }
        return makeKey(Arrays.asList(
                val.getTag(),
                Long.toString(val.getWarTime().getTime())
                )
        );
    }

    public static String keyOf(CocWarTeam val){

        if(StringUtils.isBlank(val.getClan()) || val.getWarTime() == null ){
            throw new IllegalStateException("Can not make key");
        }
        return makeKey(Arrays.asList(
                val.getClan(),
                Long.toString(val.getWarTime().getTime())
                )
        );
    }

}
