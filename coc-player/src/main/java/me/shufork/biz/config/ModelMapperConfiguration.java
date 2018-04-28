package me.shufork.biz.config;

import me.shufork.biz.converter.*;
import me.shufork.biz.domain.*;
import me.shufork.common.dto.supercell.coc.*;
import me.shufork.common.vo.PlayerAchievementsVo;
import me.shufork.common.vo.PlayerLegendStatisticsVo;
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

        modelMapper.createTypeMap(PlayerAchievementsVo.class,CocPlayerAchievement.class)
                .setConverter(new PlayerAchievementsVoConverter.ToCocPlayerAchievement());

        modelMapper.createTypeMap(TroopDto.class,CocSpell.class).setConverter(new TroopDtoConverter.ToCocSpell());

        modelMapper.createTypeMap(TroopDto.class,CocHero.class).setConverter(new TroopDtoConverter.ToCocHero());

        modelMapper.createTypeMap(TroopDto.class,CocTroop.class).setConverter(new TroopDtoConverter.ToCocTroop());

        modelMapper.createTypeMap(SeasonDto.class,CocSeasonRankHistory.class)
                .setConverter(new SeasonDtoConverter.ToCocSeasonRankHistory());

        modelMapper.createTypeMap(LeagueDto.class,CocLeague.class)
                .setConverter(new LeagueDtoConverter.ToCocLeague());

        modelMapper.createTypeMap(PlayerLegendStatisticsVo.class,CocPlayerLegendStatistic.class)
                .setConverter(new PlayerLegendStatisticsVoConverter.ToCocPlayerLegendStatistic());

        modelMapper.createTypeMap(LegendStatisticsDto.class,CocPlayerLegendStatistic.class)
                .setConverter(new LegendStatisticsDtoConverter.ToCocPlayerLegendStatistic());

        modelMapper.createTypeMap(PlayerDetailedInfoDto.class, CocPlayerDetails.class)
                .setConverter(new PlayerDetailedInfoDtoConverter.ToCocPlayerDetails());

        modelMapper.createTypeMap(PlayerBasicInfoDto.class, CocPlayer.class)
                .setConverter(new PlayerBasicInfoDtoConverter.ToCocPlayer());

        return modelMapper;
    }
}
