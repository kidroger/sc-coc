package me.shufork.biz.service;

import me.shufork.biz.domain.CocPlayerAchievement;
import me.shufork.common.dto.supercell.coc.AchievementsDto;
import me.shufork.common.service.EntityHandler;
import me.shufork.common.vo.PlayerAchievementsVo;

import java.util.List;

public interface PlayerAchievementService extends EntityHandler<PlayerAchievementsVo,CocPlayerAchievement> {

    /*CocPlayerAchievement create(String owner,AchievementsDto source);
    List<CocPlayerAchievement> create(String owner,Iterable<AchievementsDto> source);
    CocPlayerAchievement createOrGet(String owner,AchievementsDto source);
    List<CocPlayerAchievement> createOrGet(String owner,Iterable<AchievementsDto> source);*/
}
