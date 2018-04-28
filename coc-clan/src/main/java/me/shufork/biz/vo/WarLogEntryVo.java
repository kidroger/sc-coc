package me.shufork.biz.vo;

import lombok.Data;

@Data
public class WarLogEntryVo {

    private String result;
    private String endTime;
    private int teamSize;
    private String homeTeam;
    private String awayTeam;
}
