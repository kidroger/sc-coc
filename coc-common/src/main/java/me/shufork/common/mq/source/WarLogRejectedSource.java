package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface WarLogRejectedSource {
    String OUTPUT = "coc_war_log_rejected_output";

    @Output(OUTPUT)
    MessageChannel output();
}
