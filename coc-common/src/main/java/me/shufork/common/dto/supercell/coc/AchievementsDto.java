package me.shufork.common.dto.supercell.coc;

import lombok.Data;

@Data
public class AchievementsDto {

    private String name;
    private int stars;
    private int value;
    private int target;
    private String info;
    private String completionInfo;
    private String village;
}
