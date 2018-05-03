package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.PlayerTracking;
import me.shufork.biz.repository.PlayerTrackingRepository;
import me.shufork.biz.service.PlayerTracker;
import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
import me.shufork.common.dto.supercell.coc.PlayerDetailedInfoDto;
import me.shufork.common.util.DateTimeUtil;
import me.shufork.common.utils.PlayerScore;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Slf4j
public class PlayerTrackerImpl implements PlayerTracker {

    @Autowired
    private PlayerTrackingRepository playerTrackingRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlayerTracking addPlayer(PlayerBasicInfoDto player) {
        PlayerTracking entity = playerTrackingRepository.findOne(player.getTag());
        if(entity == null){
            entity = modelMapper.map(player,PlayerTracking.class);
            entity.setScore(PlayerScore.totalScore(player));
            return playerTrackingRepository.save(entity);
        }
        // no update even if name changed
        return entity;
    }

    @Override
    public PlayerTracking addOrUpdatePlayer(PlayerDetailedInfoDto player) {

        PlayerTracking entity = playerTrackingRepository.findOne(player.getTag());
        if(entity == null){
            entity = new PlayerTracking();
            entity.setPlayer(player.getTag());
        }
        entity.setScore(PlayerScore.totalScore(player));
        entity.setName(player.getName());
        entity.setLastHit(DateTimeUtil.ofJdkDate(DateTimeUtil.utc()));

        return playerTrackingRepository.save(entity);
    }
}
