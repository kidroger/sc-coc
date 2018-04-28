package me.shufork.common.mq.payload.discovery;

import lombok.Data;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;

@Data
public class ClanFoundPayload {
    private ClanBasicInfoDto clan;
}
