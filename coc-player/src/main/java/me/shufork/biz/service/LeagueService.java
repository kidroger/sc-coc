package me.shufork.biz.service;

import me.shufork.biz.domain.CocLeague;
import me.shufork.common.dto.supercell.coc.LeagueDto;
import me.shufork.common.service.EntityHandler;


public interface LeagueService extends EntityHandler<LeagueDto,CocLeague> {
    /*CocLeague create(LeagueDto league);
    CocLeague createOrUpdate(LeagueDto league);
    List<CocLeague> createOrUpdate(Iterable<LeagueDto> leagues);*/
}
