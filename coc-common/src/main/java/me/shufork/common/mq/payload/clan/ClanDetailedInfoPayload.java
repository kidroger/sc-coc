package me.shufork.common.mq.payload.clan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;

@Data
public class ClanDetailedInfoPayload {

    @JsonProperty("clan_detailed_info")
    private ClanDetailedInfoDto clanDetailedInfo;
}
