package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PlayerFoundSource {
    String OUTPUT = "coc_player_found_output";

    @Output(OUTPUT)
    MessageChannel output();
}
