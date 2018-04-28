package me.shufork.common.constants;

import me.shufork.common.dto.supercell.coc.SeasonDto;

public final class CocConstants {
    public static final char TAG_CHAR = '#';
    public static final String TAG_PREFIX = "#";
    public static final long LEAGUE_ID_INVALID = -1L;
    public static final long LOCATION_ID_INVALID = -1L;
    public static final SeasonDto NOT_IN_SEASON = new SeasonDto(-1,-1,null);

    private CocConstants(){}

}
