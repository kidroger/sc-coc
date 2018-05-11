package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocWarTeam;
import me.shufork.biz.repository.CocWarTeamRepository;
import me.shufork.biz.service.WarTeamService;
import me.shufork.biz.utils.EntityKeyUtils;
import me.shufork.biz.vo.WarLogEntryClanVo;
import me.shufork.biz.vo.WarTeamVo;
import me.shufork.common.exceptions.RecordNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class WarTeamServiceImpl implements WarTeamService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CocWarTeamRepository cocWarTeamRepository;

    @Override
    public String insertOrUpdate(WarLogEntryClanVo source) {
        CocWarTeam entity = modelMapper.map(source,CocWarTeam.class);
        final String pk= EntityKeyUtils.keyOf(entity);
        entity.setId(pk);
        cocWarTeamRepository.insertOrUpdate(entity);
        return pk;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<String> insertOrUpdate(Iterable<? extends WarLogEntryClanVo> source) {
        List<String> ids = new LinkedList<>();
        source.forEach(o->{
            ids.add(insertOrUpdate(o));
        });
        return ids;
    }
    @Transactional(readOnly = true)
    @Override
    public WarTeamVo getOneById(String id) {
        return Optional.ofNullable(findOneById(id)).orElseThrow(()-> new RecordNotFoundException("war-team.id",id));
    }

    @Transactional(readOnly = true)
    @Override
    public WarTeamVo findOneById(String id) {
        CocWarTeam found = cocWarTeamRepository.findOne(id);
        return found == null?null:modelMapper.map(found,WarTeamVo.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<WarTeamVo> findAllByClanTag(String clanTag) {
        Page<CocWarTeam> result = cocWarTeamRepository.findAllByClanOrderByWarTimeDesc(clanTag,new PageRequest(0,20));
        return result.getContent().stream().map(o->modelMapper.map(o,WarTeamVo.class)).collect(Collectors.toList());
    }

    /*
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public String create(WarLogEntryClanVo source) {
        CocWarTeam entity = modelMapper.map(source,CocWarTeam.class);
        final String pk= EntityKeyUtils.keyOf(entity);
        entity.setId(pk);
        cocWarTeamRepository.insertOrUpdate(entity);
        return pk;
    }

    @Override
    public List<String> create(Iterable<? extends WarLogEntryClanVo> source) {
        List<String> warTeams = new LinkedList<>();
        source.forEach(o->{
            warTeams.add(create(o));
        });
        return warTeams;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public String createOrUpdate(WarLogEntryClanVo source) {
        final String pk = EntityKeyUtils.keyOf(source);
        if(cocWarTeamRepository.exists(pk)){
            cocWarTeamRepository.updatePrivateInfo(pk,source.getAttacks(),source.getExpEarned());
            return pk;
        }else{
            return create(source);
        }
    }

    @Override
    public List<String> createOrUpdate(Iterable<? extends WarLogEntryClanVo> source) {
        List<String> ids = new LinkedList<>();
        source.forEach(o->{
            ids.add(createOrUpdate(o));
        });
        return ids;
    }*/

}
