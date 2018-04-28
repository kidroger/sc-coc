package me.shufork.common.dto.supercell.coc;

import lombok.Data;

@Data
public class LegendStatisticsDto {

    private int legendTrophies;
    private SeasonDto currentSeason;
    private SeasonDto previousSeason;
    private SeasonDto bestSeason;
}
