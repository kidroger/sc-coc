package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.ClanTracking;
import me.shufork.biz.repository.ClanTrackingRepository;
import me.shufork.biz.service.ClanTracker;
import me.shufork.biz.utils.TrackerCache;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import me.shufork.common.util.DateTimeUtil;
import me.shufork.common.utils.BuilderVillageScore;
import me.shufork.common.utils.HomeVillageScore;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Component
@Slf4j
public class ClanTrackerImpl implements ClanTracker {
    @Autowired
    private ClanTrackingRepository clanTrackingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired(required = false)
    @Qualifier("clanTrackerCache")
    private TrackerCache<ClanTracking.ClanTracker> clanTrackerCache;

    @Override
    public String addClan(ClanBasicInfoDto clan) {

        /*ClanTracking entity = clanTrackingRepository.findOne(clan.getTag());
        if(entity == null){
            entity = modelMapper.map(clan,ClanTracking.class);
            entity.setScore(HomeVillageScore.basicScore(clan));
            if(clanTrackerCache!=null){
                clanTrackerCache.add(clan.getTag(),new TrackerCache.SimpleClanTracker(clan.getTag(),clan.getName()));
            }
            return clanTrackingRepository.save(entity);
        }
        return entity;*/
        final String pk = clan.getTag();
        if(!clanTrackingRepository.exists(pk)){
            if(clanTrackerCache!=null){
                clanTrackerCache.add(pk,new TrackerCache.SimpleClanTracker(pk,clan.getName()));
            }
            ClanTracking entity = modelMapper.map(clan,ClanTracking.class);
            entity.setScore(HomeVillageScore.basicScore(clan));
            clanTrackingRepository.insertOrIgnore(entity);
        }
        return pk;
    }

    @Override
    public String addOrUpdateClan(ClanDetailedInfoDto clan) {
        /*ClanTracking entity = clanTrackingRepository.findOne(clan.getTag());
        if(entity == null){
            entity = new ClanTracking();
            entity.setClan(clan.getTag());
        }
        entity.setLastHit(DateTimeUtil.ofJdkDate(DateTimeUtil.utc()));
        entity.setScore(HomeVillageScore.totalScore(clan) + BuilderVillageScore.totalScore(clan));
        entity.setName(clan.getName());
        return clanTrackingRepository.save(entity);*/

        final String pk = clan.getTag();
        if(clanTrackerCache!=null){
            clanTrackerCache.remove(pk);
        }
        ClanTracking entity = new ClanTracking();
        entity.setClan(clan.getTag());
        entity.setLastHit(DateTimeUtil.ofJdkDate(DateTimeUtil.utc()));
        entity.setScore(HomeVillageScore.totalScore(clan) + BuilderVillageScore.totalScore(clan));
        entity.setName(clan.getName());
        clanTrackingRepository.insertOrUpdate(entity);
        return entity.getClan();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    @Override
    public List<ClanTracking.ClanTracker> retrieveSomeForAutoPull(int count) {
        final Date lastHit = DateTimeUtil.utc().toDate();
        Page<ClanTracking.ClanTracker> pageResult = clanTrackingRepository.findAllByOrderByLastHitAscScoreDesc(ClanTracking.ClanTracker.class,new PageRequest(0,count));
        List<ClanTracking.ClanTracker> trackers = pageResult.getContent();
        trackers.forEach(o->clanTrackingRepository.updateLastHit(o.getClan(),lastHit));
        return trackers;
    }

}
