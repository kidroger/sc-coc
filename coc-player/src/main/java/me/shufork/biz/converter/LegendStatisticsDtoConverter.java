package me.shufork.biz.converter;

import me.shufork.biz.domain.CocPlayerLegendStatistic;
import me.shufork.common.dto.supercell.coc.LegendStatisticsDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Optional;

public abstract class LegendStatisticsDtoConverter {
    public static class ToCocPlayerLegendStatistic implements Converter<LegendStatisticsDto,CocPlayerLegendStatistic>{

        @Override
        public CocPlayerLegendStatistic convert(MappingContext<LegendStatisticsDto, CocPlayerLegendStatistic> mappingContext) {
            LegendStatisticsDto source = mappingContext.getSource();
            CocPlayerLegendStatistic target = new CocPlayerLegendStatistic();
            Optional.ofNullable(source.getCurrentSeason()).ifPresent(o->{
                target.setCurrentSeasonId(o.getId());
                target.setCurrentSeasonRank(o.getRank());
                target.setCurrentSeasonTrophies(o.getTrophies());
            });
            Optional.ofNullable(source.getPreviousSeason()).ifPresent(o->{
                target.setPreviousSeasonId(o.getId());
                target.setPreviousSeasonRank(o.getRank());
                target.setPreviousSeasonTrophies(o.getTrophies());
            });
            Optional.ofNullable(source.getBestSeason()).ifPresent(o->{
                target.setBestSeasonId(o.getId());
                target.setBestSeasonRank(o.getRank());
                target.setBestSeasonTrophies(o.getTrophies());
            });
            return target;
        }
    }
}
