package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocClanDetails;
import me.shufork.biz.repository.CocClanDetailsRepository;
import me.shufork.biz.service.ClanDetailsService;
import me.shufork.common.constants.CocConstants;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class ClanDetailsServiceImpl implements ClanDetailsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CocClanDetailsRepository cocClanDetailsRepository;

    public CocClanDetails create(ClanDetailedInfoDto source) {
        CocClanDetails entity = modelMapper.map(source,CocClanDetails.class);
        //entity.setVersion(0l);
        return cocClanDetailsRepository.save(entity);
    }

    public List<CocClanDetails> create(Iterable<? extends ClanDetailedInfoDto> source) {
        List<CocClanDetails> details = new LinkedList<>();
        source.forEach(o->{
            CocClanDetails entity = modelMapper.map(o,CocClanDetails.class);
            //entity.setVersion(0l);
            details.add(entity);
        });
        return cocClanDetailsRepository.save(details);
    }

    public CocClanDetails createOrUpdate(ClanDetailedInfoDto source) {
        CocClanDetails target = cocClanDetailsRepository.findOne(source.getTag());
        if(target == null){
            return create(source);
        }
        target.setType(source.getType());
        target.setDescription(source.getDescription());
        target.setLocation(source.getLocation()==null? CocConstants.LOCATION_ID_INVALID:source.getLocation().getId());
        target.setClanPoints(source.getClanPoints());
        target.setClanVersusPoints(source.getClanVersusPoints());
        target.setRequiredTrophies(source.getRequiredTrophies());
        target.setWarFrequency(source.getWarFrequency());
        target.setWarWinStreak(source.getWarWinStreak());
        target.setWarWins(source.getWarWins());
        target.setWarTies(source.getWarTies());
        target.setWarLosses(source.getWarLosses());
        target.setWarLogPublic(source.isWarLogPublic());
        target.setTotalMembers(source.getTotalMembers());

        return cocClanDetailsRepository.save(target);
    }

    public List<CocClanDetails> createOrUpdate(Iterable<? extends ClanDetailedInfoDto> source) {
        List<CocClanDetails> list = new LinkedList<>();
        source.forEach(o->{
            list.add(createOrUpdate(o));
        });
        return list;
    }

    @Override
    public String insertOrUpdate(ClanDetailedInfoDto source) {
        final String pk = source.getTag();
        if(!cocClanDetailsRepository.exists(pk)){
            CocClanDetails entity = modelMapper.map(source,CocClanDetails.class);
            cocClanDetailsRepository.insertOrUpdate(entity);
        }else{
            CocClanDetails target = cocClanDetailsRepository.findOne(pk);
            target.setType(source.getType());
            target.setDescription(source.getDescription());
            target.setLocation(source.getLocation()==null? CocConstants.LOCATION_ID_INVALID:source.getLocation().getId());
            target.setClanPoints(source.getClanPoints());
            target.setClanVersusPoints(source.getClanVersusPoints());
            target.setRequiredTrophies(source.getRequiredTrophies());
            target.setWarFrequency(source.getWarFrequency());
            target.setWarWinStreak(source.getWarWinStreak());
            target.setWarWins(source.getWarWins());
            target.setWarTies(source.getWarTies());
            target.setWarLosses(source.getWarLosses());
            target.setWarLogPublic(source.isWarLogPublic());
            target.setTotalMembers(source.getTotalMembers());
            cocClanDetailsRepository.save(target);
        }
        return pk;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<String> insertOrUpdate(Iterable<? extends ClanDetailedInfoDto> source) {
        List<String> list = new LinkedList<>();
        source.forEach(o->{
            list.add(insertOrUpdate(o));
        });
        return list;
    }
}
