package me.shufork.biz.service;

import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;

public interface ClanWebService {
    ClanBasicInfoDto getClanBasicInfo(String tag);
    ClanDetailedInfoDto getClanDetailedInfo(String tag);
    void pullClanDetailedInfo(String tag);
}
