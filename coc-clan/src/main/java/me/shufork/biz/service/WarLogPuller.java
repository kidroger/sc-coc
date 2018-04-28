package me.shufork.biz.service;

import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;

public interface WarLogPuller {
    void checkAndPullWarLog(ClanDetailedInfoDto source);
    void checkAndPullWarLog(Iterable<ClanDetailedInfoDto> sources);
}
