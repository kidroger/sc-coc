package me.shufork.biz.service;

import me.shufork.common.mq.payload.Page;

import java.util.Optional;

public interface WarLogService {

    /**
     * load war logs
     *
     * @param clanTag coc hash tag,prefix '#' is auto added,if missing
     * @param paging
     */
    void loadWarLogs(String clanTag, Optional<Page> paging);
}
