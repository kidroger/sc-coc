package me.shufork.biz.service;

import me.shufork.biz.domain.CocTroop;
import me.shufork.common.dto.supercell.coc.TroopDto;
import me.shufork.common.service.ImmutableEntityHandler;

import java.util.List;

public interface CocTroopService extends ImmutableEntityHandler<TroopDto,CocTroop> {
    CocTroop findOneById(String id);
    List<CocTroop> findAllById(Iterable<String> ids);
}
