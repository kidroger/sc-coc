package me.shufork.biz.service;

import me.shufork.biz.domain.ClanTracking;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;

import java.util.List;

public interface ClanTracker {
    /**
     *
     * @param clan
     * @return
     */
    ClanTracking addClan(ClanBasicInfoDto clan);
    ClanTracking addOrUpdateClan(ClanDetailedInfoDto clan);

    /**
     * his method may return null
     * @return
     */
    ClanTracking.ClanTracker retrieveOneForAutoPull();

    /**
     * This method may return empty list(taken away by another thread/application )
     * @param count
     * @return
     */
    List<ClanTracking.ClanTracker> retrieveSomeForAutoPull(int count);
}
