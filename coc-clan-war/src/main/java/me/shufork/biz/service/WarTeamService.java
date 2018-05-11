package me.shufork.biz.service;

import me.shufork.biz.vo.WarLogEntryClanVo;
import me.shufork.biz.vo.WarTeamVo;
import me.shufork.common.data.FastImporter;

import java.util.List;

public interface WarTeamService extends FastImporter<WarLogEntryClanVo,String> {

    WarTeamVo getOneById(String id);
    WarTeamVo findOneById(String id);

    // FIXME : paging
    List<WarTeamVo> findAllByClanTag(String clanTag);
}
