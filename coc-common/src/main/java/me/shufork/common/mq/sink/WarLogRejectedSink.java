package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface WarLogRejectedSink {
    String INPUT = "coc_war_log_rejected_input";

    @Input(INPUT)
    SubscribableChannel input();
}
