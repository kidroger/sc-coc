package me.shufork.common.mq.source;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PlayerTroopLoadedSource {
    String OUTPUT = "coc_player_troop_loaded_output";

    @Output(OUTPUT)
    MessageChannel output();
}