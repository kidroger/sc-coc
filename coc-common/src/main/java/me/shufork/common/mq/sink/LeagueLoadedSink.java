package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface LeagueLoadedSink {

    String INPUT = "coc_league_Loaded_input";

    @Input(INPUT)
    SubscribableChannel input();
}
