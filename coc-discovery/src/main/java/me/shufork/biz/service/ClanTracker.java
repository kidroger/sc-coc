package me.shufork.biz.service;

import me.shufork.biz.domain.ClanTracking;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;

public interface ClanTracker {
    /**
     *
     * @param clan
     * @return
     */
    ClanTracking addClan(ClanBasicInfoDto clan);
    ClanTracking addOrUpdateClan(ClanDetailedInfoDto clan);

    ClanTracking.ClanTracker retrieveOneForAutoPull();
}
