package me.shufork.biz.utils;

import me.shufork.biz.domain.CocHero;
import me.shufork.biz.domain.CocPlayerAchievement;
import me.shufork.biz.domain.CocSpell;
import me.shufork.biz.domain.CocTroop;
import me.shufork.common.dto.supercell.coc.TroopDto;
import me.shufork.common.enums.CocArmyTypeEnums;
import me.shufork.common.utils.KeyBuilder;
import me.shufork.common.vo.PlayerAchievementsVo;
import me.shufork.common.vo.PlayerTroopVo;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public final class EntityKeyUtils {
    private EntityKeyUtils(){}

    public static String keyOf(PlayerAchievementsVo val){
        if(StringUtils.isBlank(val.getOwner()) || StringUtils.isBlank(val.getName()) || StringUtils.isBlank(val.getVillage())){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey (Arrays.asList(
                val.getVillage(),
                val.getOwner(),
                val.getName()
                )
        );
    }

    public static String keyOf(CocPlayerAchievement val){
        if(StringUtils.isBlank(val.getOwner()) || StringUtils.isBlank(val.getName()) || StringUtils.isBlank(val.getVillage())){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey(Arrays.asList(
                val.getVillage(),
                val.getOwner(),
                val.getName()
                )
        );
    }

    public static String keyOf(CocArmyTypeEnums type,PlayerTroopVo val){
        if(val.getMaxLevel() <= 0 || val.getLevel() <= 0 || StringUtils.isBlank(val.getName()) || StringUtils.isBlank(val.getVillage())){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey(Arrays.asList(
                val.getVillage(),
                type.getValue(),
                val.getName(),
                Integer.toString(val.getLevel()),
                Integer.toString(val.getMaxLevel())
        ));
    }
    public static String keyOf(CocArmyTypeEnums type,TroopDto val){
        if(val.getMaxLevel() <= 0 || val.getLevel() <= 0 || StringUtils.isBlank(val.getName()) || StringUtils.isBlank(val.getVillage())){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey(Arrays.asList(
                val.getVillage(),
                type.getValue(),
                val.getName(),
                Integer.toString(val.getLevel()),
                Integer.toString(val.getMaxLevel())
        ));
    }

    public static String keyOf(CocTroop val){
        if(val.getMaxLevel() <= 0 || val.getLevel() <= 0 || StringUtils.isBlank(val.getName()) || StringUtils.isBlank(val.getVillage())){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey(Arrays.asList(
                val.getVillage(),
                CocArmyTypeEnums.TROOP.getValue(),
                val.getName(),
                Integer.toString(val.getLevel()),
                Integer.toString(val.getMaxLevel())
        ));
    }

    public static String keyOf(CocSpell val){
        if(val.getMaxLevel() <= 0 || val.getLevel() <= 0 || StringUtils.isBlank(val.getName()) || StringUtils.isBlank(val.getVillage())){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey(Arrays.asList(
                val.getVillage(),
                CocArmyTypeEnums.SPELL.getValue(),
                val.getName(),
                Integer.toString(val.getLevel()),
                Integer.toString(val.getMaxLevel())
        ));
    }

    public static String keyOf(CocHero val){
        if(val.getMaxLevel() <= 0 || val.getLevel() <= 0 || StringUtils.isBlank(val.getName()) || StringUtils.isBlank(val.getVillage())){
            throw new IllegalStateException("Can not make key");
        }
        return KeyBuilder.makeKey(Arrays.asList(
                val.getVillage(),
                CocArmyTypeEnums.HERO.getValue(),
                val.getName(),
                Integer.toString(val.getLevel()),
                Integer.toString(val.getMaxLevel())
        ));
    }
}
