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

import javax.persistence.OptimisticLockException;
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
    /*
    public ClanTracking retrieveOneForAutoPull(){
        ClanTracking found = clanTrackingRepository.findFirstByOrderByLastHitAscScoreDesc();
        if(found != null){
            found.setLastHit(DateTimeUtil.utc().toDate());
            clanTrackingRepository.save(found);
        }
        return found;
    }
    */

    private ClanTracking.ClanTracker updateLastHitAndGet(){
        ClanTracking.ClanTracker found = clanTrackingRepository.findFirstByOrderByLastHitAscScoreDesc(ClanTracking.ClanTracker.class);
        if(found != null ){
            if(1 != clanTrackingRepository.updateLastHit(found.getClan(),found.getVersion(), DateTimeUtil.utc().toDate())){
                throw new OptimisticLockException(found.getClan()+"("+ found.getVersion()+")",null, found);
            }
        }
        return found;
    }

    @Override
    public ClanTracking.ClanTracker retrieveOneForAutoPull() {

        final int maxRetry = 10;
        int tried = 0;
        while (tried++ < maxRetry){
            try {
                return updateLastHitAndGet();
            }catch (OptimisticLockException e){
                log.debug("OptimisticLockException :{}.retry {}/{}",e.getMessage(),tried,maxRetry);
            }
        }
        // still no luck
        return null;
    }
    @Override
    public List<ClanTracking.ClanTracker> retrieveSomeForAutoPull(int count) {
        final Date lastHit = DateTimeUtil.utc().toDate();
        clanTrackingRepository.markLastHit(lastHit,count);
        // possibly empty list
        return clanTrackingRepository.findAllByLastHit(lastHit,ClanTracking.ClanTracker.class);
    }
}
