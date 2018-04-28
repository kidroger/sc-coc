package me.shufork.biz.converter;

import me.shufork.biz.domain.CocSeasonRankHistory;
import me.shufork.common.dto.supercell.coc.SeasonDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class SeasonDtoConverter {
    public static class ToCocSeasonRankHistory implements Converter<SeasonDto,CocSeasonRankHistory>{
        @Override
        public CocSeasonRankHistory convert(MappingContext<SeasonDto, CocSeasonRankHistory> mappingContext) {
            SeasonDto source = mappingContext.getSource();
            CocSeasonRankHistory target = new CocSeasonRankHistory();
            target.setSeasonId(source.getId());
            target.setRank(source.getRank());
            target.setTrophies(source.getTrophies());
            return target;
        }
    }
}
