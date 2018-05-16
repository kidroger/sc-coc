package me.shufork.common.constants;

import me.shufork.common.dto.supercell.coc.SeasonDto;

import java.util.Date;

public final class CocConstants {
    public static final char TAG_CHAR = '#';
    public static final String TAG_PREFIX = "#";
    public static final long LEAGUE_ID_INVALID = -1L;
    public static final long LOCATION_ID_INVALID = -1L;
    public static final SeasonDto NOT_IN_SEASON = new SeasonDto(-1,-1,null);
    public static final Date EPOCH = new Date(0l);
    public static final String EPOCH_SQL_EXPR = "1970-01-01 00:00:00";

    private CocConstants(){}

}
