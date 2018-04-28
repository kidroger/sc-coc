package me.shufork.common.mq.payload.player;

import lombok.Data;
import me.shufork.common.dto.supercell.coc.TroopDto;

import java.util.List;

@Data
public class PlayerSpellLoadedPayload{

    private String playerTag;
    private List<TroopDto> spells;
}