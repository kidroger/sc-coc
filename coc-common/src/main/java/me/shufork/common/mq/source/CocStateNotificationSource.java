package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CocStateNotificationSource {
    String OUTPUT = "coc_state_notification_output";

    @Output(OUTPUT)
    MessageChannel output();
}
