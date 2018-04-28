package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocHero;
import me.shufork.biz.domain.CocPlayerHero;
import me.shufork.biz.repository.CocPlayerHeroRepository;
import me.shufork.biz.service.CocHeroService;
import me.shufork.biz.service.PlayerHeroService;
import me.shufork.common.dto.supercell.coc.TroopDto;
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
public class PlayerHeroServiceImpl implements PlayerHeroService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CocPlayerHeroRepository cocPlayerHeroRepository;
    @Autowired
    private CocHeroService cocHeroService;

    public List<CocPlayerHero> addPlayerHeroes(String playerTag, Iterable<TroopDto> troops) {
        List<CocPlayerHero> forCreate = new LinkedList<>();
        List<CocHero> saved = cocHeroService.createOrGet(troops);
        saved.forEach( o->{
            CocPlayerHero entity = new CocPlayerHero();
            entity.setPlayer(CocHashTagUtil.ensurePrefix(playerTag));
            entity.setHero(o.getId());
            //entity.setVersion(0l);
            forCreate.add(entity);
        });
        return cocPlayerHeroRepository.save(forCreate);
    }

    @Override
    public List<CocPlayerHero> relinkPlayerHeroes(String playerTag, Iterable<TroopDto> troops) {
        List<CocPlayerHero> saved = cocPlayerHeroRepository.findAllByPlayer(CocHashTagUtil.ensurePrefix(playerTag));
        if(!saved.isEmpty()){
            cocPlayerHeroRepository.delete(saved);
        }
        return addPlayerHeroes(playerTag,troops);
    }
}
