package me.shufork.biz.service;

import me.shufork.biz.domain.CocWarTeam;
import me.shufork.biz.vo.WarLogEntryClanVo;
import me.shufork.biz.vo.WarTeamVo;

import java.util.List;

public interface WarTeamService {
    CocWarTeam create(WarLogEntryClanVo source);

    /**
     * @deprecated
     * @param source
     * @return
     */
    List<CocWarTeam> create(Iterable<? extends WarLogEntryClanVo> source);

    /**
     *
     * @param source
     * @return the pk
     */
    String createOrUpdate(WarLogEntryClanVo source);

    /**
     *
     * @deprecated
     * @param source
     * @return  the pk
     */
    List<String> createOrUpdate(Iterable<? extends WarLogEntryClanVo> source);

    CocWarTeam createOrGet(WarLogEntryClanVo source);

    /**
     * @deprecated
     * @param source
     * @return
     */
    List<CocWarTeam> createOrGet(Iterable<? extends WarLogEntryClanVo> source);

    WarTeamVo getOneById(String id);
    WarTeamVo findOneById(String id);

    // FIXME : paging
    List<WarTeamVo> findAllByClanTag(String clanTag);
}
