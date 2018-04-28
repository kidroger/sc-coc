package me.shufork.common.mq.payload.player;

import lombok.Data;
import me.shufork.common.dto.supercell.coc.AchievementsDto;

import java.util.List;

@Data
public class PlayerAchievementLoadedPayload {

    private String playerTag;
    private List<AchievementsDto> achievements;
}