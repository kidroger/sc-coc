package me.shufork.biz.service;

import me.shufork.biz.domain.CocSpell;
import me.shufork.common.dto.supercell.coc.TroopDto;
import me.shufork.common.service.ImmutableEntityHandler;

import java.util.List;

public interface CocSpellService  extends ImmutableEntityHandler<TroopDto,CocSpell> {
    CocSpell findOneById(String id);
    List<CocSpell> findAllById(Iterable<String> ids);
}
