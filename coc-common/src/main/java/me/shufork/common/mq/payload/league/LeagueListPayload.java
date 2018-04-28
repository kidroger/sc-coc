package me.shufork.common.mq.payload.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.shufork.common.dto.supercell.coc.LeagueDto;

import java.util.List;

@Data
public class LeagueListPayload {

    @JsonProperty("league_list")
    private List<LeagueDto> leagueList;
}
