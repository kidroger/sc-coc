package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface WarLogLoadedSink {
    String INPUT = "coc_war_log_loaded_input";

    @Input(INPUT)
    SubscribableChannel input();
}
