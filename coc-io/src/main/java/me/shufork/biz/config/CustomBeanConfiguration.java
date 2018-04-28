package me.shufork.biz.config;

import com.ibasco.agql.protocols.supercell.coc.webapi.CocWebApiClient;
import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocClans;
import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocLeagues;
import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocLocations;
import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocPlayers;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanConfiguration {

    @Value("${super-cell.coc.api-token}")
    private String cocWebApiToken;

    @Bean
    public CocWebApiClient cocWebApiClient(){
        return new CocWebApiClient(cocWebApiToken);
    }

    @Bean
    public CocLeagues cocLeagues(CocWebApiClient cocWebApiClient){
        return new CocLeagues(cocWebApiClient);
    }

    @Bean
    public CocClans cocClans(CocWebApiClient cocWebApiClient){
        return new CocClans(cocWebApiClient);
    }

    @Bean
    public CocLocations cocLocations(CocWebApiClient cocWebApiClient){
        return new CocLocations(cocWebApiClient);
    }

    @Bean
    public CocPlayers cocPlayers(CocWebApiClient cocWebApiClient){
        return new CocPlayers(cocWebApiClient);
    }


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        /*modelMapper.createTypeMap(User.class, UserDto.class).setConverter(context -> {
            User source = context.getSource();
            UserDto target = new UserDto();
            return target;
        });*/

        return modelMapper;
    }
}
