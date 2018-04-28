package me.shufork.biz.service;

public interface ClanDetailService {

    /**
     * load clan info
     * @param clanTag player tag,prefix '#' is auto added,if missing
     */
    void loadClanDetailedInfo(String clanTag);

}
