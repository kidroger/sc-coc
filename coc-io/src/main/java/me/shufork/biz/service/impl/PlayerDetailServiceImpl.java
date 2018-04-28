package me.shufork.biz.service.impl;

import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocPlayers;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.CocAchievements;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.CocLegendStatistics;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.CocPlayerDetailedInfo;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.CocTroop;
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
    private PlayerTroopLoadedSource playerTroopLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PlayerSpellLoadedSource playerSpellLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PlayerHeroLoadedSource playerHeroLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PlayerLegendStatisticLoadedSource playerLegendStatisticLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PlayerAchievementLoadedSource playerAchievementLoadedSource;


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

        publishPlayerAchievements(data.getTag(),data.getAchievements());
        publishPlayerLegendStatistics(data.getTag(),data.getLegendStatistics());
        publishPlayerHeroes(data.getTag(),data.getHeroes());
        publishPlayerSpells(data.getTag(),data.getSpells());
        publishPlayerTroops(data.getTag(),data.getTroops());

    }
    public void publishPlayerAchievements(String owner, List<CocAchievements> achievements){
        if(achievements!=null && !achievements.isEmpty()){
            PlayerAchievementLoadedPayload playerAchievementLoadedPayload = new PlayerAchievementLoadedPayload();
            playerAchievementLoadedPayload.setPlayerTag(owner);
            List<AchievementsDto> mappedData = achievements.stream().map(o->modelMapper.map(o,AchievementsDto.class)).collect(Collectors.toList());
            playerAchievementLoadedPayload.setAchievements(mappedData);
            playerAchievementLoadedSource.output().send(MessageBuilder.withPayload(playerAchievementLoadedPayload).build());
        }
    }
    public void publishPlayerLegendStatistics(String owner, CocLegendStatistics legendStatistics){
        if(legendStatistics!=null){
            PlayerLegendStatisticLoadedPayload playerLegendStatisticLoadedPayload = new PlayerLegendStatisticLoadedPayload();
            playerLegendStatisticLoadedPayload.setPlayerTag(owner);
            playerLegendStatisticLoadedPayload.setLegendStatistics(modelMapper.map(legendStatistics,LegendStatisticsDto.class));
            playerLegendStatisticLoadedSource.output().send(MessageBuilder.withPayload(playerLegendStatisticLoadedPayload).build());
        }
    }

    public void publishPlayerHeroes(String owner, List<CocTroop> heroes){
        if(heroes!=null && !heroes.isEmpty()){
            PlayerHeroLoadedPayload playerHeroLoadedPayload = new PlayerHeroLoadedPayload();
            playerHeroLoadedPayload.setPlayerTag(owner);
            List<TroopDto> mappedData = heroes.stream().map(o->modelMapper.map(o,TroopDto.class)).collect(Collectors.toList());
            playerHeroLoadedPayload.setHeroes(mappedData);
            playerHeroLoadedSource.output().send(MessageBuilder.withPayload(playerHeroLoadedPayload).build());
        }
    }
    public void publishPlayerSpells(String owner, List<CocTroop> spells){
        if(spells!=null && !spells.isEmpty()){
            PlayerSpellLoadedPayload playerSpellLoadedPayload = new PlayerSpellLoadedPayload();
            playerSpellLoadedPayload.setPlayerTag(owner);
            List<TroopDto> mappedData = spells.stream().map(o->modelMapper.map(o,TroopDto.class)).collect(Collectors.toList());
            playerSpellLoadedPayload.setSpells(mappedData);
            playerSpellLoadedSource.output().send(MessageBuilder.withPayload(playerSpellLoadedPayload).build());
        }
    }
    public void publishPlayerTroops(String owner, List<CocTroop> troops){
        if(troops!=null && !troops.isEmpty()){
            PlayerTroopLoadedPayload playerTroopLoadedPayload = new PlayerTroopLoadedPayload();
            playerTroopLoadedPayload.setPlayerTag(owner);
            List<TroopDto> mappedData = troops.stream().map(o->modelMapper.map(o,TroopDto.class)).collect(Collectors.toList());
            playerTroopLoadedPayload.setTroops(mappedData);
            playerTroopLoadedSource.output().send(MessageBuilder.withPayload(playerTroopLoadedPayload).build());
        }
    }
}
