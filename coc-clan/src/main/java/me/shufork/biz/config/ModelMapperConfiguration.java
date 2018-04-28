package me.shufork.biz.config;

import me.shufork.biz.converter.ClanBasicInfoDtoConverter;
import me.shufork.biz.converter.ClanDetailedInfoDtoConverter;
import me.shufork.biz.converter.WarLogEntryClanVoConverter;
import me.shufork.biz.converter.WarLogEntryVoConverter;
import me.shufork.biz.domain.CocClan;
import me.shufork.biz.domain.CocClanDetails;
import me.shufork.biz.domain.CocWarLog;
import me.shufork.biz.domain.CocWarTeam;
import me.shufork.biz.vo.WarLogEntryClanVo;
import me.shufork.biz.vo.WarLogEntryVo;
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

        modelMapper.createTypeMap(WarLogEntryClanVo.class,CocWarTeam.class)
                .setConverter(new WarLogEntryClanVoConverter.ToCocWarTeam());

        modelMapper.createTypeMap(CocWarTeam.class,WarLogEntryClanVo.class)
                .setConverter(new WarLogEntryClanVoConverter.FromCocWarTeam());

        modelMapper.createTypeMap(ClanBasicInfoDto.class,CocClan.class)
                .setConverter(new ClanBasicInfoDtoConverter.ToCocClan());

        modelMapper.createTypeMap(CocClan.class,ClanBasicInfoDto.class)
                .setConverter(new ClanBasicInfoDtoConverter.FromCocClan());

        modelMapper.createTypeMap(CocWarLog.class,WarLogEntryVo.class)
                .setConverter(new WarLogEntryVoConverter.FromCocWarLog());

        modelMapper.createTypeMap(ClanDetailedInfoDto.class,CocClanDetails.class)
                .setConverter(new ClanDetailedInfoDtoConverter.ToCocClanDetails());

        modelMapper.createTypeMap(CocClanDetails.class,ClanDetailedInfoDto.class)
                .setConverter(new ClanDetailedInfoDtoConverter.FromCocClanDetails());


        return modelMapper;
    }
}
