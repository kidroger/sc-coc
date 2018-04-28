package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PlayerDetailedInfoLoadedSource {

    String OUTPUT = "coc_player_detailed_info_loaded_output";

    @Output(OUTPUT)
    MessageChannel output();
}
