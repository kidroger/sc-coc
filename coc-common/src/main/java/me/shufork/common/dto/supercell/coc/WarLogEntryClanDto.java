package me.shufork.common.dto.supercell.coc;

import lombok.Data;

@Data
public class WarLogEntryClanDto {

    private String tag;
    private String name;
    private ClanBadgeUrlsDto badgeUrls;
    private int clanLevel;
    private int attacks;
    private int stars;
    private double destructionPercentage;
    private int expEarned;
}
