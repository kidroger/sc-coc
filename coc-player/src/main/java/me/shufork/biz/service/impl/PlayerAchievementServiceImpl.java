package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocPlayerAchievement;
import me.shufork.biz.repository.CocPlayerAchievementRepository;
import me.shufork.biz.service.PlayerAchievementService;
import me.shufork.biz.utils.EntityKeyUtils;
import me.shufork.common.vo.PlayerAchievementsVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class PlayerAchievementServiceImpl implements PlayerAchievementService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CocPlayerAchievementRepository cocPlayerAchievementRepository;

    private CocPlayerAchievement createCocAchievement_(PlayerAchievementsVo source){
        CocPlayerAchievement achievement = modelMapper.map(source,CocPlayerAchievement.class);
        achievement.setOwner(source.getOwner());
        //achievement.setVersion(0l);
        achievement.setId(EntityKeyUtils.keyOf(achievement));
        return cocPlayerAchievementRepository.save(achievement);
    }

    private List<CocPlayerAchievement> createCocAchievements_(Iterable<? extends PlayerAchievementsVo> source){
        List<CocPlayerAchievement> achievements = new LinkedList<>();
        source.forEach(o->{
            CocPlayerAchievement achievement = modelMapper.map(o,CocPlayerAchievement.class);
            achievement.setOwner(o.getOwner());
            //achievement.setVersion(0l);
            achievement.setId(EntityKeyUtils.keyOf(achievement));
            achievements.add(achievement);
        });
        return cocPlayerAchievementRepository.save(achievements);
    }

    private CocPlayerAchievement createOrGetCocAchievement_(PlayerAchievementsVo source){
        String pk = EntityKeyUtils.keyOf(source);
        CocPlayerAchievement achievement = cocPlayerAchievementRepository.findOne(pk);
        if(achievement == null){
            return createCocAchievement_(source);
        }
        return achievement;
    }

    private List<CocPlayerAchievement> createOrGetCocAchievements(Iterable<? extends PlayerAchievementsVo> source){
        List<CocPlayerAchievement> achievements = new LinkedList<>();
        source.forEach(o->{
            achievements.add(createOrGetCocAchievement_(o));
        });
        return achievements;
    }

    private CocPlayerAchievement createOrUpdateCocAchievement_(PlayerAchievementsVo source){
        String pk = EntityKeyUtils.keyOf(source);
        CocPlayerAchievement achievement = cocPlayerAchievementRepository.findOne(pk);
        if(achievement == null){
            return createCocAchievement_(source);
        }
        achievement.setCompletionInfo(source.getCompletionInfo());
        // do not update owner ,village and name
        achievement.setStars(source.getStars());
        achievement.setTarget(source.getTarget());
        achievement.setValue(source.getValue());

        return cocPlayerAchievementRepository.save(achievement);
    }
    private List<CocPlayerAchievement> createOrUpdateCocAchievements_(Iterable<? extends PlayerAchievementsVo> source){
        List<CocPlayerAchievement> achievements = new LinkedList<>();
        source.forEach(o->{
            achievements.add(createOrUpdateCocAchievement_(o));
        });
        return achievements;
    }


    @Override
    public CocPlayerAchievement create(PlayerAchievementsVo source) {
        return createCocAchievement_(source);
    }

    @Override
    public List<CocPlayerAchievement> create(Iterable<? extends PlayerAchievementsVo> source) {
        return createCocAchievements_(source);
    }

    @Override
    public CocPlayerAchievement createOrUpdate(PlayerAchievementsVo source) {
        return createOrUpdateCocAchievement_(source);
    }

    @Override
    public List<CocPlayerAchievement> createOrUpdate(Iterable<? extends PlayerAchievementsVo> source) {
        return createOrUpdateCocAchievements_(source);
    }
}
