package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocClan;
import me.shufork.biz.repository.CocClanRepository;
import me.shufork.biz.service.ClanService;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class ClanServiceImpl implements ClanService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CocClanRepository cocClanRepository;
    @Override
    public CocClan create(ClanBasicInfoDto source) {
        CocClan entity = modelMapper.map(source,CocClan.class);
        //entity.setVersion(0l);
        return cocClanRepository.save(entity);
    }

    @Override
    public List<CocClan> create(Iterable<? extends ClanBasicInfoDto> source) {
        List<CocClan> forCreate = new LinkedList<>();
        source.forEach(o->{
            CocClan entity = modelMapper.map(o,CocClan.class);
            //entity.setVersion(0l);
            forCreate.add(entity);
        });
        return cocClanRepository.save(forCreate);
    }

    @Override
    public CocClan createOrUpdate(ClanBasicInfoDto source) {
        CocClan entity = cocClanRepository.findOne(source.getTag());
        if(entity == null){
            return create(source);
        }

        entity.setName(source.getName());
        entity.setClanLevel(source.getClanLevel());
        entity.setBadgeSmall(source.getBadgeUrls().getSmall());
        entity.setBadgeMedium(source.getBadgeUrls().getMedium());
        entity.setBadgeLarge(source.getBadgeUrls().getLarge());
        return cocClanRepository.save(entity);
    }

    @Override
    public List<CocClan> createOrUpdate(Iterable<? extends ClanBasicInfoDto> source) {
        List<CocClan> list = new LinkedList<>();
        source.forEach(o->{
            list.add(createOrUpdate(o));
        });
        return list;
    }
}
