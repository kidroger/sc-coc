package me.shufork.biz.service;

public interface ClanDetailIoService {

    /**
     * load clan info
     * @param clanTag player tag,prefix '#' is auto added,if missing
     */
    void loadClanDetailedInfo(String clanTag);

}
