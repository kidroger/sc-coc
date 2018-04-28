package me.shufork.common.mq.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Page {

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("paging_cursor")
    private String cursor;

    @JsonProperty("after")
    private boolean after;
}
