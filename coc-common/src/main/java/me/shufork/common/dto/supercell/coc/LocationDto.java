package me.shufork.common.dto.supercell.coc;

import lombok.Data;

@Data
public class LocationDto {
    private long id;
    private String name;
    private boolean isCountry;
    private String countryCode;
}
