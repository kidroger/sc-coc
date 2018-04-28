package me.shufork.common.vo;

import lombok.Data;

@Data
public class PlayerTroopVo {
    private String name;
    private int level;
    private int maxLevel;
    private String village;

    private String owner;
}
