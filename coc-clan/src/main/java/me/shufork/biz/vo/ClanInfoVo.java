package me.shufork.biz.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ClanInfoVo {
    private Date lastUpdate;

    private String tag;
    private String name;
    private int clanLevel;
    private String badgeSmall;
    private String badgeMedium;
    private String badgeLarge;

    private String type;
    private String description;
    private long location;
    private int clanPoints;
    private int clanVersusPoints;
    private int requiredTrophies;
    private String warFrequency;
    private int warWinStreak;
    private int warWins;
    private int warTies;
    private int warLosses;
    private boolean warLogPublic;
    private int totalMembers;
}
