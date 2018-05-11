package me.shufork.biz.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class WarTeamVo {
    @JsonIgnore
    private String id;
    private String opponent;
    private Date warTime;
    private String clan;
    private String name;
    private String badgeSmall;
    private String badgeMedium;
    private String badgeLarge;
    private int clanLevel;
    private int attacks;
    private int stars;
    private double destructionPercentage;
    private int expEarned;
}
