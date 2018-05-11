package me.shufork.biz.config;

import me.shufork.biz.converter.ClanBasicInfoDtoConverter;
import me.shufork.biz.converter.ClanDetailedInfoDtoConverter;
import me.shufork.biz.converter.ClanInfoVoConverter;
import me.shufork.biz.domain.CocClan;
import me.shufork.biz.domain.CocClanDetails;
import me.shufork.biz.vo.ClanInfoVo;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
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

        modelMapper.createTypeMap(ClanBasicInfoDto.class,CocClan.class)
                .setConverter(new ClanBasicInfoDtoConverter.ToCocClan());

        modelMapper.createTypeMap(CocClan.class,ClanBasicInfoDto.class)
                .setConverter(new ClanBasicInfoDtoConverter.FromCocClan());

        modelMapper.createTypeMap(ClanDetailedInfoDto.class,CocClanDetails.class)
                .setConverter(new ClanDetailedInfoDtoConverter.ToCocClanDetails());

        modelMapper.createTypeMap(CocClanDetails.class,ClanDetailedInfoDto.class)
                .setConverter(new ClanDetailedInfoDtoConverter.FromCocClanDetails());

        modelMapper.createTypeMap(CocClanDetails.class,ClanInfoVo.class)
                .setConverter(new ClanInfoVoConverter.FromCocClanDetails());

        modelMapper.createTypeMap(ClanDetailedInfoDto.class,ClanInfoVo.class)
                .setConverter(new ClanInfoVoConverter.FromClanDetailedInfoDto());

        return modelMapper;
    }
}
