package me.shufork.common.dto.supercell.coc;

import lombok.Data;

@Data
public class WarLogEntryDto {
    private String result;
    private String endTime;
    private int teamSize;
    private WarLogEntryClanDto clan;
    private WarLogEntryClanDto opponent;
}
