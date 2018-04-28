package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CocStateNotificationSink {
    String INPUT = "coc_state_notification_input";

    @Input(INPUT)
    SubscribableChannel input();
}
