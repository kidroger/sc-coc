package me.shufork.biz.converter;

import me.shufork.biz.domain.CocLeague;
import me.shufork.common.dto.supercell.coc.LeagueDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class LeagueDtoConverter {
    public static class ToCocLeague implements Converter<LeagueDto,CocLeague> {
        @Override
        public CocLeague convert(MappingContext<LeagueDto, CocLeague> mappingContext) {
            LeagueDto source = mappingContext.getSource();
            CocLeague target = new CocLeague();
            target.setLeagueId(source.getId());
            target.setName(source.getName());
            target.setIconTiny(source.getIconUrls() == null?null:source.getIconUrls().getTiny());
            target.setIconSmall(source.getIconUrls() == null?null:source.getIconUrls().getSmall());
            target.setIconMedium(source.getIconUrls() == null?null:source.getIconUrls().getMedium());
            return target;
        }
    }
}
