package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ClanFoundSource {
    String OUTPUT = "coc_clan_found_output";

    @Output(OUTPUT)
    MessageChannel output();
}
