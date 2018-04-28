package me.shufork.biz.service;

import me.shufork.biz.domain.CocPlayerHero;
import me.shufork.common.dto.supercell.coc.TroopDto;

import java.util.List;

public interface PlayerHeroService {

    List<CocPlayerHero> relinkPlayerHeroes(String playerTag, Iterable<TroopDto> troops);
}
