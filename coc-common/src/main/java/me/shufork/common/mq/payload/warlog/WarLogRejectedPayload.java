package me.shufork.common.mq.payload.warlog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WarLogRejectedPayload {

    @JsonProperty("clan_tag")
    private String clanTag;
}
