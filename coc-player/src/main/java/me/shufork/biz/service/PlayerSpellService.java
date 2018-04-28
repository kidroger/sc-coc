package me.shufork.biz.service;

import me.shufork.biz.domain.CocPlayerSpell;
import me.shufork.common.dto.supercell.coc.TroopDto;

import java.util.List;

public interface PlayerSpellService{
    List<CocPlayerSpell> relinkPlayerSpells(String playerTag, Iterable<TroopDto> troops);
}
