package me.shufork.common.mq.payload.warlog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.page.PageMeta;
import lombok.Data;
import me.shufork.common.dto.supercell.coc.WarLogEntryDto;

import java.util.List;

@Data
public class WarLogListPayload {

    @JsonProperty("war_log_list")
    private List<WarLogEntryDto> warLogList;

    //private String before;

    //private String after;
    //private PageMeta pageMeta;
    //private Page pageMeta;
    private PageMeta pageMeta;
}
