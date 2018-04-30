package me.shufork.biz.converter;
import me.shufork.biz.domain.CocClanDetails;
import me.shufork.biz.vo.ClanInfoVo;
import me.shufork.common.constants.CocConstants;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import me.shufork.common.util.DateTimeUtil;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class ClanInfoVoConverter {
    public static class FromCocClanDetails implements Converter<CocClanDetails,ClanInfoVo>{
        @Override
        public ClanInfoVo convert(MappingContext<CocClanDetails, ClanInfoVo> context) {
            CocClanDetails source = context.getSource();
            ClanInfoVo target = new ClanInfoVo();
            target.setLastUpdate(source.getModifiedTime());
            target.setTag(source.getTag());
            target.setType(source.getType());
            target.setDescription(source.getDescription());
            target.setLocation(source.getLocation());
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

            // note: update basic info outside
            return target;
        }
    }


    public static class FromClanDetailedInfoDto implements Converter<ClanDetailedInfoDto,ClanInfoVo>{
        @Override
        public ClanInfoVo convert(MappingContext<ClanDetailedInfoDto, ClanInfoVo> context) {
            ClanDetailedInfoDto source = context.getSource();
            ClanInfoVo target = new ClanInfoVo();
            target.setLastUpdate(DateTimeUtil.utc().toDate());

            target.setTag(source.getTag());
            target.setName(source.getName());
            target.setClanLevel(source.getClanLevel());
            target.setBadgeSmall(source.getBadgeUrls().getSmall());
            target.setBadgeMedium(source.getBadgeUrls().getMedium());
            target.setBadgeLarge(source.getBadgeUrls().getLarge());

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
            return target;
        }
    }
}
