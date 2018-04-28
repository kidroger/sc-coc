package me.shufork.common.vo;

import lombok.Data;
import me.shufork.common.dto.supercell.coc.SeasonDto;

@Data
public class PlayerLegendStatisticsVo {
    private int legendTrophies;
    private SeasonDto currentSeason;
    private SeasonDto previousSeason;
    private SeasonDto bestSeason;

    private String owner;
}
