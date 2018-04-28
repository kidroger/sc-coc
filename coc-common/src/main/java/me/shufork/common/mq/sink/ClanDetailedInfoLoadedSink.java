package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ClanDetailedInfoLoadedSink {
    String INPUT = "coc_clan_detailed_info_loaded_input";

    @Input(INPUT)
    SubscribableChannel input();
}
