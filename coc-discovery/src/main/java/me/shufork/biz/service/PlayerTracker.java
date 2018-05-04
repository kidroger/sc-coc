package me.shufork.biz.service;

import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
import me.shufork.common.dto.supercell.coc.PlayerDetailedInfoDto;

public interface PlayerTracker {

    String addPlayer(PlayerBasicInfoDto player);
    String addOrUpdatePlayer(PlayerDetailedInfoDto player);
}
