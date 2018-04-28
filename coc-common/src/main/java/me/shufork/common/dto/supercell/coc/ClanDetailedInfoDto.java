package me.shufork.common.dto.supercell.coc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClanDetailedInfoDto {
    private String tag;
    private String name;
    private int clanLevel;
    private ClanBadgeUrlsDto badgeUrls;

    private String type;
    private String description;
    private LocationDto location;
    private int clanPoints;
    private int clanVersusPoints;
    private int requiredTrophies;
    private String warFrequency;
    private int warWinStreak;
    private int warWins;
    private int warTies;
    private int warLosses;
    @JsonProperty("isWarLogPublic")
    private boolean warLogPublic;
    @JsonProperty("members")
    private int totalMembers;
    @JsonProperty("memberList")
    private List<PlayerBasicInfoDto> clanMembers = new ArrayList<>();
}
