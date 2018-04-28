package me.shufork.biz.service.impl;

import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocPlayers;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.CocPlayerDetailedInfo;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.PlayerDetailService;
import me.shufork.common.dto.supercell.coc.AchievementsDto;
import me.shufork.common.dto.supercell.coc.LegendStatisticsDto;
import me.shufork.common.dto.supercell.coc.PlayerDetailedInfoDto;
import me.shufork.common.dto.supercell.coc.TroopDto;
import me.shufork.common.mq.payload.player.*;
import me.shufork.common.mq.source.*;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static me.shufork.common.utils.CocHashTagUtil.ensurePrefix;

@Service
@Slf4j
public class PlayerDetailServiceImpl implements PlayerDetailService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PlayerDetailedInfoLoadedSource playerDetailedInfoLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    PlayerTroopLoadedSource playerTroopLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    PlayerSpellLoadedSource playerSpellLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    PlayerHeroLoadedSource playerHeroLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    PlayerLegendStatisticLoadedSource playerLegendStatisticLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    PlayerAchievementLoadedSource playerAchievementLoadedSource;


    @Autowired
    private CocPlayers playerApi;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void loadPlayerDetailedInfo(String playerTag) {

        playerApi.getPlayerInfo(ensurePrefix(playerTag))
                .thenAcceptAsync( o -> publishPlayerDetailedInfo(o))
                .exceptionally(e -> {log.warn(e.getMessage(),e);return null;});
    }



    private void publishPlayerDetailedInfo(CocPlayerDetailedInfo data){
        if(data == null || StringUtils.isBlank(data.getTag()) ){
            log.error("bad data  (except CocPlayerDetailedInfo ):{}",data);
            return;
        }
        PlayerDetailedInfoPayload playerDetailedInfoPayload = new PlayerDetailedInfoPayload();
        playerDetailedInfoPayload.setPlayerDetailedInfo(modelMapper.map(data,PlayerDetailedInfoDto.class) );
        playerDetailedInfoLoadedSource.output().send(MessageBuilder.withPayload(playerDetailedInfoPayload).build());

        if(data.getLegendStatistics()!=null){

            PlayerLegendStatisticLoadedPayload playerLegendStatisticLoadedPayload = new PlayerLegendStatisticLoadedPayload();
            playerLegendStatisticLoadedPayload.setPlayerTag(data.getTag());
            playerLegendStatisticLoadedPayload.setLegendStatistics(modelMapper.map(data.getLegendStatistics(),LegendStatisticsDto.class));
            playerLegendStatisticLoadedSource.output().send(MessageBuilder.withPayload(playerLegendStatisticLoadedPayload).build());
        }

        if(data.getAchievements()!=null && !data.getAchievements().isEmpty()){

            PlayerAchievementLoadedPayload playerAchievementLoadedPayload = new PlayerAchievementLoadedPayload();
            playerAchievementLoadedPayload.setPlayerTag(data.getTag());
            List<AchievementsDto> mappedData = data.getAchievements().stream().map(o->modelMapper.map(o,AchievementsDto.class)).collect(Collectors.toList());
            playerAchievementLoadedPayload.setAchievements(mappedData);
            playerAchievementLoadedSource.output().send(MessageBuilder.withPayload(playerAchievementLoadedPayload).build());
        }

        if(data.getHeroes()!=null && !data.getHeroes().isEmpty()){

            PlayerHeroLoadedPayload playerHeroLoadedPayload = new PlayerHeroLoadedPayload();
            playerHeroLoadedPayload.setPlayerTag(data.getTag());
            List<TroopDto> mappedData = data.getHeroes().stream().map(o->modelMapper.map(o,TroopDto.class)).collect(Collectors.toList());
            playerHeroLoadedPayload.setHeroes(mappedData);
            playerHeroLoadedSource.output().send(MessageBuilder.withPayload(playerHeroLoadedPayload).build());
        }

        if(data.getSpells()!=null && !data.getSpells().isEmpty()){

            PlayerSpellLoadedPayload playerSpellLoadedPayload = new PlayerSpellLoadedPayload();
            playerSpellLoadedPayload.setPlayerTag(data.getTag());
            List<TroopDto> mappedData = data.getSpells().stream().map(o->modelMapper.map(o,TroopDto.class)).collect(Collectors.toList());
            playerSpellLoadedPayload.setSpells(mappedData);
            playerSpellLoadedSource.output().send(MessageBuilder.withPayload(playerSpellLoadedPayload).build());
        }

        if(data.getTroops()!=null && !data.getTroops().isEmpty()){

            PlayerTroopLoadedPayload playerTroopLoadedPayload = new PlayerTroopLoadedPayload();
            playerTroopLoadedPayload.setPlayerTag(data.getTag());
            List<TroopDto> mappedData = data.getTroops().stream().map(o->modelMapper.map(o,TroopDto.class)).collect(Collectors.toList());
            playerTroopLoadedPayload.setTroops(mappedData);
            playerTroopLoadedSource.output().send(MessageBuilder.withPayload(playerTroopLoadedPayload).build());
        }

    }
}
