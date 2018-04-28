package me.shufork.common.utils;

import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;

public abstract class BuilderVillageScore {

    public static final int pushScore(ClanDetailedInfoDto source){
        return  source.getClanVersusPoints();
    }

    public static final int totalScore(ClanDetailedInfoDto source){
        return pushScore(source);
    }
}
