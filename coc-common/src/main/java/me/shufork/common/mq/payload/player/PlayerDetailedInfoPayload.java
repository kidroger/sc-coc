package me.shufork.common.mq.payload.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.shufork.common.dto.supercell.coc.PlayerDetailedInfoDto;

@Data
public class PlayerDetailedInfoPayload {

    @JsonProperty("player_detailed_info")
    private PlayerDetailedInfoDto playerDetailedInfo;
}
