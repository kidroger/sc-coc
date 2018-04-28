package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocTroop;
import me.shufork.biz.repository.CocTroopRepository;
import me.shufork.biz.service.CocTroopService;
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
public class CocTroopServiceImpl implements CocTroopService {
    @Autowired
    private CocTroopRepository cocTroopRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CocTroop create(TroopDto source) {
        CocTroop entity = modelMapper.map(source,CocTroop.class);
        //entity.setVersion(0l);
        return cocTroopRepository.save(entity);
    }

    @Override
    public List<CocTroop> create(Iterable<? extends TroopDto> source) {
        List<CocTroop> list = new LinkedList<>();
        source.forEach(o->{
            CocTroop entity = modelMapper.map(source,CocTroop.class);
            //entity.setVersion(0l);
            list.add(entity);
        });
        return cocTroopRepository.save(list);
    }

    @Override
    public CocTroop createOrGet(TroopDto source) {
        final String pk = EntityKeyUtils.keyOf(CocArmyTypeEnums.TROOP,source);
        CocTroop val = cocTroopRepository.findOne(pk);
        return val == null? create(source):val;
    }

    @Override
    public List<CocTroop> createOrGet(Iterable<? extends TroopDto> source) {
        List<CocTroop> list = new LinkedList<>();
        source.forEach(o->{
            list.add(createOrGet(o));
        });
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public CocTroop findOneById(String id) {
        return cocTroopRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CocTroop> findAllById(Iterable<String> ids) {
        return cocTroopRepository.findAll(ids);
    }
}
