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
    public CocWarTeam create(WarLogEntryClanVo source) {
        CocWarTeam entity = modelMapper.map(source,CocWarTeam.class);
        final String pk= EntityKeyUtils.keyOf(entity);
        entity.setId(pk);
        return cocWarTeamRepository.save(entity);
    }

    @Override
    public List<CocWarTeam> create(Iterable<? extends WarLogEntryClanVo> source) {
        List<CocWarTeam> warTeams = new LinkedList<>();
        source.forEach(o->{
            CocWarTeam warTeam = create(o);
            if(warTeam!=null){
                warTeams.add(warTeam);
            }
        });
        return warTeams;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String createOrUpdate(WarLogEntryClanVo source) {
        CocWarTeam entity = modelMapper.map(source,CocWarTeam.class);
        final String pk= EntityKeyUtils.keyOf(entity);
        entity.setId(pk);
        cocWarTeamRepository.insertOrUpdate(entity);
        return pk;
        /*final String pk = EntityKeyUtils.keyOf(source);
        if(!cocWarTeamRepository.exists(pk)){
            create(source);
            return pk;
        }
        cocWarTeamRepository.updatePrivateInfo(pk,source.getAttacks(),source.getExpEarned());
        return pk;*/
    }

    @Override
    public List<String> createOrUpdate(Iterable<? extends WarLogEntryClanVo> source) {
        List<String> ids = new LinkedList<>();
        source.forEach(o->{
            ids.add(createOrUpdate(o));
        });
        return ids;
    }

    @Override
    public CocWarTeam createOrGet(WarLogEntryClanVo source) {
        final String pk = EntityKeyUtils.keyOf(source);
        //CocWarTeam entity = cocWarTeamRepository.findOne(pk);
        return Optional.ofNullable(cocWarTeamRepository.findOne(pk)).orElse(create(source));
    }

    @Override
    public List<CocWarTeam> createOrGet(Iterable<? extends WarLogEntryClanVo> source) {
        List<CocWarTeam> warTeams = new LinkedList<>();
        source.forEach(o->{
            warTeams.add( createOrGet(o) );
        });
        return warTeams;
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
}
