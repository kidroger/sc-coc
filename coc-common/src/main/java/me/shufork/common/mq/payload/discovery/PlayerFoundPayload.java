package me.shufork.common.mq.payload.discovery;

import lombok.Data;
import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;

@Data
public class PlayerFoundPayload {
    private PlayerBasicInfoDto player;
}
