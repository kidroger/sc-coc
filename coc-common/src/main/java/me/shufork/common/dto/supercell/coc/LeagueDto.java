package me.shufork.common.dto.supercell.coc;

import lombok.Data;

@Data
public class LeagueDto {
    private long id;
    private String name;
    private LeagueIconUrlsDto iconUrls;
}
