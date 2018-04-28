package me.shufork.biz.service;

import me.shufork.biz.domain.CocHero;
import me.shufork.common.dto.supercell.coc.TroopDto;
import me.shufork.common.service.ImmutableEntityHandler;

import java.util.List;

public interface CocHeroService extends ImmutableEntityHandler<TroopDto,CocHero> {
    CocHero findOneById(String id);
    List<CocHero> findAllById(Iterable<String> ids);
}
