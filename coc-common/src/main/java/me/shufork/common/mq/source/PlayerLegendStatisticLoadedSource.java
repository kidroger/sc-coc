package me.shufork.common.mq.source;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PlayerLegendStatisticLoadedSource {

    String OUTPUT = "coc_player_legend_statistic_loaded_output";

    @Output(OUTPUT)
    MessageChannel output();
}