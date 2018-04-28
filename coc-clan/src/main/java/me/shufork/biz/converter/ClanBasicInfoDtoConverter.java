package me.shufork.biz.converter;

import me.shufork.biz.domain.CocClan;
import me.shufork.common.dto.supercell.coc.ClanBadgeUrlsDto;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class ClanBasicInfoDtoConverter {
    public static class ToCocClan implements Converter<ClanBasicInfoDto,CocClan>{

        @Override
        public CocClan convert(MappingContext<ClanBasicInfoDto, CocClan> mappingContext) {
            ClanBasicInfoDto source = mappingContext.getSource();
            CocClan target = new CocClan();

            target.setTag(source.getTag());
            target.setName(source.getName());
            target.setClanLevel(source.getClanLevel());
            target.setBadgeSmall(source.getBadgeUrls().getSmall());
            target.setBadgeMedium(source.getBadgeUrls().getMedium());
            target.setBadgeLarge(source.getBadgeUrls().getLarge());

            return target;
        }
    }

    public static class FromCocClan implements Converter<CocClan,ClanBasicInfoDto>{

        @Override
        public ClanBasicInfoDto convert(MappingContext<CocClan, ClanBasicInfoDto> mappingContext) {
            CocClan source = mappingContext.getSource();
            ClanBasicInfoDto target = new ClanBasicInfoDto();

            target.setTag(source.getTag());
            target.setName(source.getName());
            target.setClanLevel(source.getClanLevel());

            ClanBadgeUrlsDto clanBadgeUrlsDto =  new ClanBadgeUrlsDto();
            clanBadgeUrlsDto.setLarge(source.getBadgeLarge());
            clanBadgeUrlsDto.setMedium(source.getBadgeMedium());
            clanBadgeUrlsDto.setSmall(source.getBadgeSmall());
            target.setBadgeUrls(clanBadgeUrlsDto);
            return target;
        }
    }
}
