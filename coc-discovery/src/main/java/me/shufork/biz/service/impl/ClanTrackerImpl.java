package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.ClanTracking;
import me.shufork.biz.repository.ClanTrackingRepository;
import me.shufork.biz.service.ClanTracker;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import me.shufork.common.util.DateTimeUtil;
import me.shufork.common.utils.BuilderVillageScore;
import me.shufork.common.utils.HomeVillageScore;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Slf4j
public class ClanTrackerImpl implements ClanTracker {
    @Autowired
    private ClanTrackingRepository clanTrackingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ClanTracking addClan(ClanBasicInfoDto clan) {

        ClanTracking entity = clanTrackingRepository.findOne(clan.getTag());
        if(entity == null){
            entity = modelMapper.map(clan,ClanTracking.class);
            //entity.setVersion(0l);
            entity.setScore(HomeVillageScore.basicScore(clan));
            return clanTrackingRepository.save(entity);
        }
        return entity;
    }

    @Override
    public ClanTracking addOrUpdateClan(ClanDetailedInfoDto clan) {
        ClanTracking entity = clanTrackingRepository.findOne(clan.getTag());
        if(entity == null){
            entity = new ClanTracking();
            //entity.setVersion(0l);
            entity.setClan(clan.getTag());
        }
        entity.setLastHit(DateTimeUtil.ofJdkDate(DateTimeUtil.utc()));
        entity.setScore(HomeVillageScore.totalScore(clan) + BuilderVillageScore.totalScore(clan));
        entity.setName(clan.getName());
        return clanTrackingRepository.save(entity);
    }

    @Override
    public ClanTracking.ClanTracker retrieveOneForAutoPull() {
        ClanTracking.ClanTracker found = clanTrackingRepository.findFirstByOrderByLastHitAscScoreDesc(ClanTracking.ClanTracker.class);
        if(found != null){
            clanTrackingRepository.updateLastHit(found.getClan(),DateTimeUtil.utc().toDate());
        }
        return found;
        /*
        ClanTracking found = clanTrackingRepository.findFirstByOrderByLastHitAscScoreDesc();
        if(found != null){
            found.setLastHit(DateTimeUtil.utc().toDate());
            clanTrackingRepository.save(found);
        }
        return found;
        */
    }
}
