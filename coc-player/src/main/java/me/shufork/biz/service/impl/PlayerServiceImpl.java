package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocPlayer;
import me.shufork.biz.repository.CocPlayerRepository;
import me.shufork.biz.service.PlayerService;
import me.shufork.common.constants.CocConstants;
import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
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
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private CocPlayerRepository cocPlayerRepository;
    @Autowired
    private ModelMapper modelMapper;

    private CocPlayer updatePlayer(PlayerBasicInfoDto source,CocPlayer cocPlayer){
        cocPlayer.setName(source.getName());
        cocPlayer.setRole(source.getRole());
        cocPlayer.setExpLevel(source.getExpLevel());
        cocPlayer.setTrophies(source.getTrophies());
        cocPlayer.setVersusTrophies(source.getVersusTrophies());
        cocPlayer.setClanRank(source.getClanRank());
        cocPlayer.setPreviousClanRank(source.getPreviousClanRank());
        cocPlayer.setTotalDonations(source.getTotalDonations());
        cocPlayer.setTotalDonationsReceived(source.getTotalDonationsReceived());
        cocPlayer.setLeague(source.getLeague()==null? CocConstants.LEAGUE_ID_INVALID:source.getLeague().getId());

        return cocPlayerRepository.save(cocPlayer);
    }

    @Override
    public CocPlayer createOrUpdate(PlayerBasicInfoDto source) {
        assert CocHashTagUtil.hasPrefix(source.getTag());

        final String playerTag = source.getTag();
        CocPlayer cocPlayer = cocPlayerRepository.findOne(playerTag);
        if(cocPlayer == null){
            return create(source);
        }
        return updatePlayer(source,cocPlayer);
    }

    @Override
    public CocPlayer create(PlayerBasicInfoDto source) {
        CocPlayer cocPlayer = modelMapper.map(source,CocPlayer.class);
        //cocPlayer.setVersion(0l);
        return cocPlayerRepository.save(cocPlayer);
    }

    @Override
    public List<CocPlayer> createOrUpdate(Iterable<? extends PlayerBasicInfoDto> source) {
        List<CocPlayer> cocPlayers = new LinkedList<>();
        source.forEach(o->{
            cocPlayers.add(createOrUpdate(o));
        });
        return cocPlayers;
    }

    @Override
    public List<CocPlayer> create(Iterable<? extends PlayerBasicInfoDto> source) {
        List<CocPlayer> cocPlayers = new LinkedList<>();
        source.forEach(o->{
            CocPlayer cocPlayer = modelMapper.map(source,CocPlayer.class);
            //cocPlayer.setVersion(0l);
            cocPlayers.add(cocPlayer);
        });
        return cocPlayerRepository.save(cocPlayers);
    }
}
