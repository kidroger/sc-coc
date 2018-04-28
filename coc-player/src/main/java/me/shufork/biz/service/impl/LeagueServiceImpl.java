package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocLeague;
import me.shufork.biz.repository.CocLeagueRepository;
import me.shufork.biz.service.LeagueService;
import me.shufork.common.dto.supercell.coc.LeagueDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class LeagueServiceImpl implements LeagueService {
    @Autowired
    private CocLeagueRepository cocLeagueRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CocLeague create(LeagueDto league) {
        CocLeague cocLeague = modelMapper.map(league,CocLeague.class);
        //cocLeague.setVersion(0l);
        return cocLeagueRepository.save(cocLeague);
    }

    @Override
    public CocLeague createOrUpdate(LeagueDto league) {
        CocLeague target = cocLeagueRepository.findOne(league.getId());
        if(target == null){
            return create(league);
        }
        target.setName(league.getName());
        target.setIconTiny(league.getIconUrls() == null?null:league.getIconUrls().getTiny());
        target.setIconSmall(league.getIconUrls() == null?null:league.getIconUrls().getSmall());
        target.setIconMedium(league.getIconUrls() == null?null:league.getIconUrls().getMedium());
        return cocLeagueRepository.save(target);
    }

    @Override
    public List<CocLeague> createOrUpdate(Iterable<? extends LeagueDto> source) {

        List<CocLeague> cocLeagueList = new LinkedList<>();
        source.forEach(o->{
            cocLeagueList.add(createOrUpdate(o));
        });
        return cocLeagueList;
    }

    @Override
    public List<CocLeague> create(Iterable<? extends LeagueDto> source) {
        List<CocLeague> cocLeagueList = new LinkedList<>();
        source.forEach(o->{
            CocLeague cocLeague = modelMapper.map(o,CocLeague.class);
            //cocLeague.setVersion(0l);
            cocLeagueList.add(cocLeague);
        });
        return cocLeagueRepository.save(cocLeagueList);
    }
}
