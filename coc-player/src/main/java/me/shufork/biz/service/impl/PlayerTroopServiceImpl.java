package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocPlayerTroop;
import me.shufork.biz.domain.CocTroop;
import me.shufork.biz.repository.CocPlayerTroopRepository;
import me.shufork.biz.service.CocTroopService;
import me.shufork.biz.service.PlayerTroopService;
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
public class PlayerTroopServiceImpl implements PlayerTroopService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CocPlayerTroopRepository cocPlayerTroopRepository;

    @Autowired
    private CocTroopService cocTroopService;


    public List<CocPlayerTroop> addPlayerTroops(String playerTag, Iterable<TroopDto> troops) {
        List<CocPlayerTroop> forCreate = new LinkedList<>();
        List<CocTroop> saved = cocTroopService.createOrGet(troops);
        saved.forEach( o->{
            CocPlayerTroop entity = new CocPlayerTroop();
            //entity.setVersion(0l);
            entity.setPlayer(CocHashTagUtil.ensurePrefix(playerTag));
            entity.setTroop(o.getId());
            forCreate.add(entity);
        });
        return cocPlayerTroopRepository.save(forCreate);
    }

    @Override
    public List<CocPlayerTroop> relinkPlayerTroops(String playerTag,Iterable<TroopDto> troops) {
        List<CocPlayerTroop> saved = cocPlayerTroopRepository.findAllByPlayer(CocHashTagUtil.ensurePrefix(playerTag));
        if(!saved.isEmpty()){
            cocPlayerTroopRepository.delete(saved);
        }
        return addPlayerTroops(playerTag,troops);
    }

}
