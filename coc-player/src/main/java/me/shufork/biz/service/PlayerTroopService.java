package me.shufork.biz.service;

import me.shufork.biz.domain.CocPlayerTroop;
import me.shufork.common.dto.supercell.coc.TroopDto;
import me.shufork.common.service.EntityHandler;
import me.shufork.common.vo.PlayerTroopVo;

import java.util.List;

public interface PlayerTroopService {
    List<CocPlayerTroop> relinkPlayerTroops(String playerTag,Iterable<TroopDto> troops);
}
