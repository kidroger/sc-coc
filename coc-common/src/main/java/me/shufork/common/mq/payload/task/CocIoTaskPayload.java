package me.shufork.common.mq.payload.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.shufork.common.enums.CocIoTaskEnums;
import me.shufork.common.mq.payload.Page;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CocIoTaskPayload {

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty("goal")
    private CocIoTaskEnums goal;

    @JsonProperty("paging")
    private Page paging;
}
