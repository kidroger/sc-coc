package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ClanDetailedInfoLoadedSource {
    String OUTPUT = "coc_clan_detailed_info_loaded_output";

    @Output(OUTPUT)
    MessageChannel output();
}
