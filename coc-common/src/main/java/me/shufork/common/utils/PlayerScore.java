package me.shufork.common.utils;

import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
import me.shufork.common.dto.supercell.coc.PlayerDetailedInfoDto;

public abstract class PlayerScore {
    public static final int totalScore(PlayerBasicInfoDto source){
        int score = source.getExpLevel()*100 ;
        score += source.getTrophies();
        score += source.getVersusTrophies();
        return score;
    }
    public static final int totalScore(PlayerDetailedInfoDto source){
        int score = (source.getBuilderHallLevel() + source.getTownHallLevel()) * 1000;
        score += source.getExpLevel()*100 ;
        score += source.getTrophies();
        score += source.getVersusTrophies();
        score += source.getWarStars()*1000 ;
        score += source.getBestVersusTrophies();
        score += source.getBestTrophies();
        return score;
    }
}
