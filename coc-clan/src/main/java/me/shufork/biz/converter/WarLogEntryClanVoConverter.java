package me.shufork.biz.converter;

import me.shufork.biz.domain.CocWarTeam;
import me.shufork.biz.vo.WarLogEntryClanVo;
import me.shufork.common.dto.supercell.coc.ClanBadgeUrlsDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class WarLogEntryClanVoConverter {
    public static class ToCocWarTeam implements Converter<WarLogEntryClanVo,CocWarTeam>{

        @Override
        public CocWarTeam convert(MappingContext<WarLogEntryClanVo, CocWarTeam> mappingContext) {
            WarLogEntryClanVo source = mappingContext.getSource();
            CocWarTeam target = new CocWarTeam();

            target.setOpponent(source.getOpponent());
            target.setWarTime(source.getWarTime());
            target.setClan(source.getTag());
            target.setName(source.getName());
            target.setBadgeSmall(source.getBadgeUrls().getSmall());
            target.setBadgeMedium(source.getBadgeUrls().getMedium());
            target.setBadgeLarge(source.getBadgeUrls().getLarge());
            target.setClanLevel(source.getClanLevel());
            target.setAttacks(source.getAttacks());
            target.setStars(source.getStars());
            target.setDestructionPercentage(source.getDestructionPercentage());
            target.setExpEarned(source.getExpEarned());

            return target;
        }
    }

    public static class FromCocWarTeam implements Converter<CocWarTeam,WarLogEntryClanVo>{

        @Override
        public WarLogEntryClanVo convert(MappingContext<CocWarTeam, WarLogEntryClanVo> mappingContext) {
            CocWarTeam source = mappingContext.getSource();
            WarLogEntryClanVo target = new WarLogEntryClanVo();

            target.setOpponent(source.getOpponent());
            target.setWarTime(source.getWarTime());
            target.setTag(source.getClan());
            target.setName(source.getName());

            ClanBadgeUrlsDto clanBadgeUrlsDto =  new ClanBadgeUrlsDto();
            clanBadgeUrlsDto.setLarge(source.getBadgeLarge());
            clanBadgeUrlsDto.setMedium(source.getBadgeMedium());
            clanBadgeUrlsDto.setSmall(source.getBadgeSmall());
            target.setBadgeUrls(clanBadgeUrlsDto);

            target.setClanLevel(source.getClanLevel());
            target.setAttacks(source.getAttacks());
            target.setStars(source.getStars());
            target.setDestructionPercentage(source.getDestructionPercentage());
            target.setExpEarned(source.getExpEarned());

            return target;
        }
    }

}
