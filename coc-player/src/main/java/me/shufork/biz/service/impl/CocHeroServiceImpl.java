package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocHero;
import me.shufork.biz.repository.CocHeroRepository;
import me.shufork.biz.service.CocHeroService;
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
public class CocHeroServiceImpl implements CocHeroService {
    @Autowired
    private CocHeroRepository cocHeroRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CocHero create(TroopDto source) {
        CocHero entity = modelMapper.map(source,CocHero.class);
        //entity.setVersion(0l);
        return cocHeroRepository.save(entity);
    }

    @Override
    public List<CocHero> create(Iterable<? extends TroopDto> source) {
        List<CocHero> list = new LinkedList<>();
        source.forEach(o->{
            CocHero entity = modelMapper.map(source,CocHero.class);
            //entity.setVersion(0l);
            list.add(entity);
        });
        return cocHeroRepository.save(list);
    }

    @Override
    public CocHero createOrGet(TroopDto source) {
        final String pk = EntityKeyUtils.keyOf(CocArmyTypeEnums.HERO,source);
        CocHero val = cocHeroRepository.findOne(pk);
        return val == null? create(source):val;
    }

    @Override
    public List<CocHero> createOrGet(Iterable<? extends TroopDto> source) {
        List<CocHero> list = new LinkedList<>();
        source.forEach(o->{
            list.add(createOrGet(o));
        });
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public CocHero findOneById(String id) {
        return cocHeroRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CocHero> findAllById(Iterable<String> ids) {
        return cocHeroRepository.findAll(ids);
    }
}
