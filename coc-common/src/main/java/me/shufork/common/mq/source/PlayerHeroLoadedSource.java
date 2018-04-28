package me.shufork.common.mq.source;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PlayerHeroLoadedSource {

    String OUTPUT = "coc_player_hero_loaded_output";

    @Output(OUTPUT)
    MessageChannel output();
}