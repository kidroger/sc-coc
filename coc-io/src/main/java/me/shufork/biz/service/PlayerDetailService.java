package me.shufork.biz.service;

public interface PlayerDetailService {

    /**
     * load player info
     * @param playerTag player tag,prefix '#' is auto added,if missing
     */
    void loadPlayerDetailedInfo(String playerTag);
}
