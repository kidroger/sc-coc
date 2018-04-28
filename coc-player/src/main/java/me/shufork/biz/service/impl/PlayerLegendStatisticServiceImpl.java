package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocPlayerLegendStatistic;
import me.shufork.biz.repository.CocPlayerLegendStatisticRepository;
import me.shufork.biz.service.PlayerLegendStatisticService;
import me.shufork.common.constants.CocConstants;
import me.shufork.common.dto.supercell.coc.SeasonDto;
import me.shufork.common.vo.PlayerLegendStatisticsVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class PlayerLegendStatisticServiceImpl implements PlayerLegendStatisticService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CocPlayerLegendStatisticRepository cocPlayerLegendStatisticRepository;

    @Override
    public CocPlayerLegendStatistic create(PlayerLegendStatisticsVo source) {
        CocPlayerLegendStatistic entity = modelMapper.map(source,CocPlayerLegendStatistic.class);
        //entity.setVersion(0l);
        return cocPlayerLegendStatisticRepository.save(entity);
    }

    @Override
    public List<CocPlayerLegendStatistic> create(Iterable<? extends PlayerLegendStatisticsVo> source) {
        List<CocPlayerLegendStatistic> list = new LinkedList<>();
        source.forEach(o->{
            CocPlayerLegendStatistic entity = modelMapper.map(o,CocPlayerLegendStatistic.class);
            //entity.setVersion(0l);
            list.add(entity);
        });
        return cocPlayerLegendStatisticRepository.save(list);
    }

    @Override
    public CocPlayerLegendStatistic createOrUpdate(PlayerLegendStatisticsVo source) {
        CocPlayerLegendStatistic entity = cocPlayerLegendStatisticRepository.findOneByPlayer(source.getOwner());
        if(entity == null){
            return create(source);
        }

        SeasonDto current = Optional.ofNullable(source.getCurrentSeason()).orElse(CocConstants.NOT_IN_SEASON);
        SeasonDto previous = Optional.ofNullable(source.getPreviousSeason()).orElse(CocConstants.NOT_IN_SEASON);
        SeasonDto best = Optional.ofNullable(source.getBestSeason()).orElse(CocConstants.NOT_IN_SEASON);

        entity.setCurrentSeasonId(current.getId());
        entity.setCurrentSeasonRank(current.getRank());
        entity.setCurrentSeasonTrophies(current.getTrophies());

        entity.setPreviousSeasonId(previous.getId());
        entity.setPreviousSeasonRank(previous.getRank());
        entity.setPreviousSeasonTrophies(previous.getTrophies());

        entity.setBestSeasonId(best.getId());
        entity.setBestSeasonRank(best.getRank());
        entity.setBestSeasonTrophies(best.getTrophies());
        return cocPlayerLegendStatisticRepository.save(entity);
    }

    @Override
    public List<CocPlayerLegendStatistic> createOrUpdate(Iterable<? extends PlayerLegendStatisticsVo> source) {
        List<CocPlayerLegendStatistic> list = new LinkedList<>();
        source.forEach(o->{
            list.add(createOrUpdate(o));
        });
        return list;
    }
}
