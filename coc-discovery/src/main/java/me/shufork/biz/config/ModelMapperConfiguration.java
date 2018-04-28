package me.shufork.biz.config;
import java.util.Date;

import me.shufork.biz.domain.ClanTracking;
import me.shufork.biz.domain.PlayerTracking;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
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

        modelMapper.createTypeMap(PlayerBasicInfoDto.class,PlayerTracking.class).setConverter( mappingContext -> {
            PlayerBasicInfoDto source = mappingContext.getSource();
            PlayerTracking target = new PlayerTracking();

            target.setPlayer(source.getTag());
            target.setName(source.getName());
            target.setScore(0);
            target.setLastHit(null);
            return target;
        });
        modelMapper.createTypeMap(ClanBasicInfoDto.class,ClanTracking.class).setConverter(mappingContext -> {
            ClanBasicInfoDto source = mappingContext.getSource();
            ClanTracking target = new ClanTracking();

            target.setClan(source.getTag());
            target.setName(source.getName());
            target.setScore(0);
            target.setLastHit(null);
            return target;
        });
        return modelMapper;
    }
}
