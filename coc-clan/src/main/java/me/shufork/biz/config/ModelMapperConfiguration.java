package me.shufork.biz.config;
import me.shufork.biz.vo.WarLogEntryVo;
import me.shufork.common.dto.supercell.coc.WarLogEntryClanDto;
import me.shufork.biz.domain.CocWarLog;
import me.shufork.biz.domain.CocWarTeam;
import me.shufork.biz.vo.WarLogEntryClanVo;
import me.shufork.common.dto.supercell.coc.*;

import java.util.Date;
import me.shufork.biz.domain.CocClan;
import me.shufork.biz.domain.CocClanDetails;
import me.shufork.common.constants.CocConstants;
import me.shufork.common.utils.CocDateTimeUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // note ignore version,createdTime,modifiedTime for domain object

        modelMapper.createTypeMap(WarLogEntryClanVo.class,CocWarTeam.class).setConverter(mappingContext -> {
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
        });
        modelMapper.createTypeMap(CocWarTeam.class,WarLogEntryClanVo.class).setConverter(mappingContext -> {
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
        });

        modelMapper.createTypeMap(ClanBasicInfoDto.class,CocClan.class).setConverter(mappingContext -> {
            ClanBasicInfoDto source = mappingContext.getSource();
            CocClan target = new CocClan();

            target.setTag(source.getTag());
            target.setName(source.getName());
            target.setClanLevel(source.getClanLevel());
            target.setBadgeSmall(source.getBadgeUrls().getSmall());
            target.setBadgeMedium(source.getBadgeUrls().getMedium());
            target.setBadgeLarge(source.getBadgeUrls().getLarge());

            return target;
        });

        modelMapper.createTypeMap(CocWarLog.class,WarLogEntryVo.class).setConverter(mappingContext -> {
            CocWarLog source = mappingContext.getSource();
            WarLogEntryVo target = new WarLogEntryVo();
            target.setResult(source.getResult());
            target.setEndTime(CocDateTimeUtil.format(source.getEndTime()));
            target.setTeamSize(source.getTeamSize());
            target.setHomeTeam(source.getHomeTeam());
            target.setAwayTeam(source.getAwayTeam());
            return target;
        });

        modelMapper.createTypeMap(CocClan.class,ClanBasicInfoDto.class).setConverter(mappingContext -> {
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
        });
        modelMapper.createTypeMap(CocClanDetails.class,ClanDetailedInfoDto.class).setConverter(mappingContext -> {
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
        });

        modelMapper.createTypeMap(ClanDetailedInfoDto.class,CocClanDetails.class).setConverter(mappingContext -> {
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
        });


        return modelMapper;
    }
}
