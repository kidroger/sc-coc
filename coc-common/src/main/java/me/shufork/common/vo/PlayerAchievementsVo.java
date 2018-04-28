package me.shufork.common.vo;

import lombok.Data;

@Data
public class PlayerAchievementsVo {
    private String name;
    private int stars;
    private int value;
    private int target;
    private String info;
    private String completionInfo;
    private String village;

    private String owner;
}
