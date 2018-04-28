package me.shufork.biz.converter;

import me.shufork.biz.domain.CocPlayerDetails;
import me.shufork.common.dto.supercell.coc.PlayerDetailedInfoDto;
import me.shufork.common.utils.CocHashTagUtil;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class PlayerDetailedInfoDtoConverter {
    public static class ToCocPlayerDetails implements Converter<PlayerDetailedInfoDto,CocPlayerDetails>{

        @Override
        public CocPlayerDetails convert(MappingContext<PlayerDetailedInfoDto, CocPlayerDetails> mappingContext) {
            PlayerDetailedInfoDto source = mappingContext.getSource();
            CocPlayerDetails target = new CocPlayerDetails();
            target.setTag(CocHashTagUtil.ensurePrefix(source.getTag()));
            target.setAttackWins(source.getAttackWins());
            target.setDefenseWins(source.getDefenseWins());
            target.setClan(CocHashTagUtil.ensurePrefix(source.getClan().getTag()));
            target.setBestTrophies(source.getBestTrophies());
            target.setWarStars(source.getWarStars());
            target.setTownHallLevel(source.getTownHallLevel());
            target.setBuilderHallLevel(source.getBuilderHallLevel());
            target.setBestVersusTrophies(source.getBestVersusTrophies());
            target.setVersusBattleWins(source.getVersusBattleWins());
            return target;
        }
    }
}
