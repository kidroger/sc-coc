package me.shufork.biz.utils;

import me.shufork.biz.domain.CocWarTeam;
import me.shufork.biz.vo.WarLogEntryClanVo;
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

    public static String keyOf(WarLogEntryClanVo val){
        if(StringUtils.isBlank(val.getTag()) || val.getWarTime() == null ){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey(Arrays.asList(
                val.getTag(),
                Long.toString(val.getWarTime().getTime())
                )
        );
    }

    public static String keyOf(CocWarTeam val){

        if(StringUtils.isBlank(val.getClan()) || val.getWarTime() == null ){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey(Arrays.asList(
                val.getClan(),
                Long.toString(val.getWarTime().getTime())
                )
        );
    }

}
