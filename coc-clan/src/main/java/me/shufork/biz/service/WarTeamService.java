package me.shufork.biz.service;

import me.shufork.biz.domain.CocWarTeam;
import me.shufork.biz.vo.WarLogEntryClanVo;
import me.shufork.biz.vo.WarTeamVo;
import me.shufork.common.service.EntityHandler;
import me.shufork.common.service.ImmutableEntityHandler;

import java.util.List;

public interface WarTeamService extends ImmutableEntityHandler<WarLogEntryClanVo, CocWarTeam>,EntityHandler<WarLogEntryClanVo, CocWarTeam> {
    WarTeamVo getOneById(String id);
    WarTeamVo findOneById(String id);

    // FIXME : paging
    List<WarTeamVo> findAllByClanTag(String clanTag);
}
