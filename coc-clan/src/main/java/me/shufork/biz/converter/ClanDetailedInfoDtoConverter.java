package me.shufork.biz.converter;

import me.shufork.biz.domain.CocClanDetails;
import me.shufork.common.constants.CocConstants;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class ClanDetailedInfoDtoConverter {
    public static class ToCocClanDetails implements Converter<ClanDetailedInfoDto,CocClanDetails> {

        @Override
        public CocClanDetails convert(MappingContext<ClanDetailedInfoDto, CocClanDetails> mappingContext) {
            ClanDetailedInfoDto source = mappingContext.getSource();
            CocClanDetails target = new CocClanDetails();
            target.setTag(source.getTag());
            target.setType(source.getType());
            target.setDescription(source.getDescription());
            target.setLocation(source.getLocation()==null? CocConstants.LOCATION_ID_INVALID:source.getLocation().getId());
            target.setClanPoints(source.getClanPoints());
            target.setClanVersusPoints(source.getClanVersusPoints());
            target.setRequiredTrophies(source.getRequiredTrophies());
            target.setWarFrequency(source.getWarFrequency());
            target.setWarWinStreak(source.getWarWinStreak());
            target.setWarWins(source.getWarWins());
            target.setWarTies(source.getWarTies());
            target.setWarLosses(source.getWarLosses());
            target.setWarLogPublic(source.isWarLogPublic());
            target.setTotalMembers(source.getTotalMembers());

            //clan member ignored
            return target;
        }
    }
    public static class FromCocClanDetails implements Converter<CocClanDetails,ClanDetailedInfoDto> {

        @Override
        public ClanDetailedInfoDto convert(MappingContext<CocClanDetails, ClanDetailedInfoDto> mappingContext) {
            CocClanDetails source = mappingContext.getSource();
            ClanDetailedInfoDto target = new ClanDetailedInfoDto();
            target.setTag(source.getTag());
            target.setType(source.getType());
            target.setDescription(source.getDescription());
            // TODO : location
            target.setLocation(null);
            target.setClanPoints(source.getClanPoints());
            target.setClanVersusPoints(source.getClanVersusPoints());
            target.setRequiredTrophies(source.getRequiredTrophies());
            target.setWarFrequency(source.getWarFrequency());
            target.setWarWinStreak(source.getWarWinStreak());
            target.setWarWins(source.getWarWins());
            target.setWarTies(source.getWarTies());
            target.setWarLosses(source.getWarLosses());
            target.setWarLogPublic(source.isWarLogPublic());
            target.setTotalMembers(source.getTotalMembers());

            //clan member ignored
            return target;
        }
    }
}
