package me.shufork.biz.service;

import me.shufork.biz.domain.PlayerTracking;
import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
import me.shufork.common.dto.supercell.coc.PlayerDetailedInfoDto;

public interface PlayerTracker {

    PlayerTracking addPlayer(PlayerBasicInfoDto player);
    PlayerTracking addOrUpdatePlayer(PlayerDetailedInfoDto player);
}
