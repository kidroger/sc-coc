package me.shufork.common.mq.payload.player;

import lombok.Data;
import me.shufork.common.dto.supercell.coc.LegendStatisticsDto;

@Data
public class PlayerLegendStatisticLoadedPayload {

    private String playerTag;
    private LegendStatisticsDto legendStatistics;
}