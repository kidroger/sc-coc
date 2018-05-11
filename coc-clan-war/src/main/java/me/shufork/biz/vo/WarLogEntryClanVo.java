package me.shufork.biz.vo;

import lombok.Data;
import me.shufork.common.dto.supercell.coc.ClanBadgeUrlsDto;

import java.util.Date;

@Data
public class WarLogEntryClanVo {
    private Date warTime;
    private String opponent;

    private String tag;
    private String name;
    private ClanBadgeUrlsDto badgeUrls;
    private int clanLevel;
    private int attacks;
    private int stars;
    private double destructionPercentage;
    private int expEarned;
}
