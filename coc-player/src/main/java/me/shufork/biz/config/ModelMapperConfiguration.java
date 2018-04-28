package me.shufork.biz.config;
import me.shufork.biz.domain.*;
import me.shufork.biz.utils.EntityKeyUtils;
import me.shufork.common.constants.CocConstants;
import me.shufork.common.dto.supercell.coc.*;
import me.shufork.common.utils.CocHashTagUtil;
import me.shufork.common.vo.PlayerAchievementsVo;
import me.shufork.common.vo.PlayerLegendStatisticsVo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // note ignore version,createdTime,modifiedTime for domain object

        modelMapper.createTypeMap(PlayerAchievementsVo.class,CocPlayerAchievement.class).setConverter(mappingContext -> {
            PlayerAchievementsVo source = mappingContext.getSource();
            CocPlayerAchievement target = new CocPlayerAchievement();
            target.setName(source.getName());
            target.setStars(source.getStars());
            target.setValue(source.getValue());
            target.setTarget(source.getTarget());
            target.setCompletionInfo(source.getCompletionInfo());
            target.setVillage(source.getVillage());
            // set id outside

            return target;
        });
        modelMapper.createTypeMap(TroopDto.class,CocSpell.class).setConverter(mappingContext -> {
            TroopDto source = mappingContext.getSource();
            CocSpell target = new CocSpell();
            target.setName(source.getName());
            target.setLevel(source.getLevel());
            target.setMaxLevel(source.getMaxLevel());
            target.setVillage(source.getVillage());
            // set id at last
            target.setId(EntityKeyUtils.keyOf(target));
            return target;
        });
        modelMapper.createTypeMap(TroopDto.class,CocHero.class).setConverter(mappingContext -> {
            TroopDto source = mappingContext.getSource();
            CocHero target = new CocHero();
            target.setName(source.getName());
            target.setLevel(source.getLevel());
            target.setMaxLevel(source.getMaxLevel());
            target.setVillage(source.getVillage());
            // set id at last
            target.setId(EntityKeyUtils.keyOf(target));
            return target;
        });
        modelMapper.createTypeMap(TroopDto.class,CocTroop.class).setConverter(mappingContext -> {
            TroopDto source = mappingContext.getSource();
            CocTroop target = new CocTroop();
            target.setName(source.getName());
            target.setLevel(source.getLevel());
            target.setMaxLevel(source.getMaxLevel());
            target.setVillage(source.getVillage());
            // set id at last
            target.setId(EntityKeyUtils.keyOf(target));
            return target;
        });

        modelMapper.createTypeMap(SeasonDto.class,CocSeasonRankHistory.class).setConverter(mappingContext -> {
            SeasonDto source = mappingContext.getSource();
            CocSeasonRankHistory target = new CocSeasonRankHistory();
            target.setSeasonId(source.getId());
            target.setRank(source.getRank());
            target.setTrophies(source.getTrophies());
            return target;
        });
        modelMapper.createTypeMap(LeagueDto.class,CocLeague.class).setConverter(mappingContext -> {
            LeagueDto source = mappingContext.getSource();
            CocLeague target = new CocLeague();
            target.setLeagueId(source.getId());
            target.setName(source.getName());
            target.setIconTiny(source.getIconUrls() == null?null:source.getIconUrls().getTiny());
            target.setIconSmall(source.getIconUrls() == null?null:source.getIconUrls().getSmall());
            target.setIconMedium(source.getIconUrls() == null?null:source.getIconUrls().getMedium());
            return target;
        });
        modelMapper.createTypeMap(PlayerLegendStatisticsVo.class,CocPlayerLegendStatistic.class).setConverter(mappingContext -> {
            PlayerLegendStatisticsVo source = mappingContext.getSource();
            CocPlayerLegendStatistic target = new CocPlayerLegendStatistic();
            target.setPlayer(source.getOwner());
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
        });

        modelMapper.createTypeMap(LegendStatisticsDto.class,CocPlayerLegendStatistic.class).setConverter(mappingContext -> {
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
        });
        modelMapper.createTypeMap(PlayerDetailedInfoDto.class, CocPlayerDetails.class).setConverter(mappingContext -> {
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
        } );

        modelMapper.createTypeMap(PlayerBasicInfoDto.class, CocPlayer.class).setConverter(mappingContext -> {
            PlayerBasicInfoDto source = mappingContext.getSource();
            CocPlayer target = new CocPlayer();
            target.setTag(CocHashTagUtil.ensurePrefix(source.getTag()));
            target.setName(source.getName());
            target.setRole(source.getRole());
            target.setExpLevel(source.getExpLevel());
            //CocLeague cocLeague = source.getLeague() ==null?null:modelMapper.map(source.getLeague(),CocLeague.class);
            target.setLeague(source.getLeague()==null? CocConstants.LEAGUE_ID_INVALID:source.getLeague().getId());
            target.setTrophies(source.getTrophies());
            target.setVersusTrophies(source.getVersusTrophies());
            target.setClanRank(source.getClanRank());
            target.setPreviousClanRank(source.getPreviousClanRank());
            target.setTotalDonations(source.getTotalDonations());
            target.setTotalDonationsReceived(source.getTotalDonationsReceived());
            //CocPlayerDetails details = modelMapper.map(source,CocPlayerDetails.class);
            //target.setDetails(details);
            return target;
        });

        return modelMapper;
    }
}
