package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocPlayerDetails;
import me.shufork.biz.repository.CocPlayerDetailsRepository;
import me.shufork.biz.service.PlayerDetailsService;
import me.shufork.common.dto.supercell.coc.PlayerDetailedInfoDto;
import me.shufork.common.utils.CocHashTagUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class PlayerDetailsServiceImpl implements PlayerDetailsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CocPlayerDetailsRepository cocPlayerDetailsRepository;

    @Override
    public CocPlayerDetails createOrUpdate(PlayerDetailedInfoDto source) {
        CocPlayerDetails cocPlayerDetails = cocPlayerDetailsRepository.findOne(source.getTag());
        if(cocPlayerDetails == null){
            return create(source);
        }
        return updatePlayerDetails(source,cocPlayerDetails);
    }

    @Override
    public CocPlayerDetails create(PlayerDetailedInfoDto source) {
        CocPlayerDetails cocPlayerDetails = modelMapper.map(source,CocPlayerDetails.class);
        //cocPlayerDetails.setVersion(0l);
        return cocPlayerDetailsRepository.save(cocPlayerDetails);
    }

    @Override
    public List<CocPlayerDetails> createOrUpdate(Iterable<? extends PlayerDetailedInfoDto> source) {
        List<CocPlayerDetails> list = new LinkedList<>();
        source.forEach(o->{
            list.add(createOrUpdate(o));
        });
        return list;
    }

    @Override
    public List<CocPlayerDetails> create(Iterable<? extends PlayerDetailedInfoDto> source) {
        List<CocPlayerDetails> list = new LinkedList<>();
        source.forEach(o->{
            CocPlayerDetails cocPlayerDetails = modelMapper.map(source,CocPlayerDetails.class);
            //cocPlayerDetails.setVersion(0l);
            list.add(cocPlayerDetails);
        });
        return cocPlayerDetailsRepository.save(list);
    }

    private CocPlayerDetails updatePlayerDetails(PlayerDetailedInfoDto source,CocPlayerDetails details){
        details.setAttackWins(source.getAttackWins());
        details.setDefenseWins(source.getDefenseWins());
        details.setClan(CocHashTagUtil.ensurePrefix(source.getClan().getTag()));
        details.setBestTrophies(source.getBestTrophies());
        details.setWarStars(source.getWarStars());
        details.setTownHallLevel(source.getTownHallLevel());
        details.setBuilderHallLevel(source.getBuilderHallLevel());
        details.setBestVersusTrophies(source.getBestVersusTrophies());
        details.setVersusBattleWins(source.getVersusBattleWins());

        return details;
    }
}
