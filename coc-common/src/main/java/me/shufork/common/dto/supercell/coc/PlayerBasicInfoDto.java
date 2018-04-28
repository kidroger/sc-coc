package me.shufork.common.dto.supercell.coc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayerBasicInfoDto {
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
}
