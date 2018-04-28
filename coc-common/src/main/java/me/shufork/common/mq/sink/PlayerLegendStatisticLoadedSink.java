package me.shufork.common.mq.sink;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PlayerLegendStatisticLoadedSink {

    String INPUT = "coc_player_legend_statistic_loaded_input";

    @Input(INPUT)
    SubscribableChannel input();
}