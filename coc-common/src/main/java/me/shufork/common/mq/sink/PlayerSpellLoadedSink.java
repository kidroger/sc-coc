package me.shufork.common.mq.sink;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PlayerSpellLoadedSink {

    String INPUT = "coc_player_spell_Loaded_input";

    @Input(INPUT)
    SubscribableChannel input();
}