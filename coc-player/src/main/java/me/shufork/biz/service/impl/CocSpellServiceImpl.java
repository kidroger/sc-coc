package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocSpell;
import me.shufork.biz.repository.CocSpellRepository;
import me.shufork.biz.service.CocSpellService;
import me.shufork.biz.utils.EntityKeyUtils;
import me.shufork.common.dto.supercell.coc.TroopDto;
import me.shufork.common.enums.CocArmyTypeEnums;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
@Component
@Slf4j
public class CocSpellServiceImpl implements CocSpellService {
    @Autowired
    private CocSpellRepository cocSpellRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CocSpell create(TroopDto source) {
        CocSpell entity = modelMapper.map(source,CocSpell.class);
        //entity.setVersion(0l);
        return cocSpellRepository.save(entity);
    }

    @Override
    public List<CocSpell> create(Iterable<? extends TroopDto> source) {
        List<CocSpell> list = new LinkedList<>();
        source.forEach(o->{
            CocSpell entity = modelMapper.map(source,CocSpell.class);
            //entity.setVersion(0l);
            list.add(entity);
        });
        return cocSpellRepository.save(list);
    }

    @Override
    public CocSpell createOrGet(TroopDto source) {
        final String pk = EntityKeyUtils.keyOf(CocArmyTypeEnums.SPELL,source);
        CocSpell val = cocSpellRepository.findOne(pk);
        return val == null? create(source):val;
    }

    @Override
    public List<CocSpell> createOrGet(Iterable<? extends TroopDto> source) {
        List<CocSpell> list = new LinkedList<>();
        source.forEach(o->{
            list.add(createOrGet(o));
        });
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public CocSpell findOneById(String id) {
        return cocSpellRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CocSpell> findAllById(Iterable<String> ids) {
        return cocSpellRepository.findAll(ids);
    }
}
