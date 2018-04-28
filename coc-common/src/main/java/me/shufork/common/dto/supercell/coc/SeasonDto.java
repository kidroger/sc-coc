package me.shufork.common.dto.supercell.coc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonDto {
    private int rank;
    private int trophies;
    private String id;
}
