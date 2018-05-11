package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocPlayerSpell;
import me.shufork.biz.domain.CocSpell;
import me.shufork.biz.repository.CocPlayerSpellRepository;
import me.shufork.biz.service.CocSpellService;
import me.shufork.biz.service.PlayerSpellService;
import me.shufork.common.dto.supercell.coc.TroopDto;
import me.shufork.common.utils.CocHashTagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class PlayerSpellServiceImpl implements PlayerSpellService {
    @Autowired
    private CocPlayerSpellRepository cocPlayerSpellRepository;
    @Autowired
    private CocSpellService cocSpellService;

    public List<CocPlayerSpell> addPlayerSpells(String playerTag, Iterable<TroopDto> troops) {
        List<CocPlayerSpell> forCreate = new LinkedList<>();
        List<CocSpell> saved = cocSpellService.createOrGet(troops);
        saved.forEach( o->{
            CocPlayerSpell entity = new CocPlayerSpell();
            //entity.setVersion(0l);
            entity.setPlayer(CocHashTagUtil.ensurePrefix(playerTag));
            entity.setSpell(o.getId());
            forCreate.add(entity);
        });
        return cocPlayerSpellRepository.save(forCreate);
    }

    @Override
    public List<CocPlayerSpell> relinkPlayerSpells(String playerTag, Iterable<TroopDto> troops) {
        List<CocPlayerSpell> saved = cocPlayerSpellRepository.findAllByPlayer(CocHashTagUtil.ensurePrefix(playerTag));
        if(!saved.isEmpty()){
            cocPlayerSpellRepository.delete(saved);
        }
        return addPlayerSpells(playerTag,troops);
    }
}
