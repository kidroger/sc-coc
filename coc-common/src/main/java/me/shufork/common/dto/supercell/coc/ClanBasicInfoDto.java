package me.shufork.common.dto.supercell.coc;

import lombok.Data;

@Data
public class ClanBasicInfoDto {
    private String tag;
    private String name;
    private int clanLevel;
    private ClanBadgeUrlsDto badgeUrls;
}
