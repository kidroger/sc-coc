package me.shufork.common.dto.supercell.coc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlayerDetailedInfoDto {
    private String tag;
    private String name;
    private String role;
    private int expLevel;
    private LeagueDto league;
    private int trophies;
    private int versusTrophies;
    private int clanRank;
    private int previousClanRank;
    @JsonProperty("donations")
    private int totalDonations;
    @JsonProperty("donationsReceived")
    private int totalDonationsReceived;

    private int attackWins;
    private int defenseWins;
    private ClanBasicInfoDto clan;
    private int bestTrophies;
    private int warStars;
    private int townHallLevel;
    private int builderHallLevel;
    private int bestVersusTrophies;
    private int versusBattleWins;
    private LegendStatisticsDto legendStatistics;
    private List<AchievementsDto> achievements;
    private List<TroopDto> troops;
    private List<TroopDto> heroes;
    private List<TroopDto> spells;
}
