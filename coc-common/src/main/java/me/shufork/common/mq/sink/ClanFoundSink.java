package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ClanFoundSink {
    String INPUT = "coc_clan_found_input";

    @Input(INPUT)
    SubscribableChannel input();
}
