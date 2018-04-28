package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PlayerFoundSink {
    String INPUT = "coc_player_found_input";

    @Input(INPUT)
    SubscribableChannel input();
}
