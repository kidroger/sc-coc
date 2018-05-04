package me.shufork.biz.service;

public interface PlayerDetailIoService {

    /**
     * load player info
     * @param playerTag player tag,prefix '#' is auto added,if missing
     */
    void loadPlayerDetailedInfo(String playerTag);
}
