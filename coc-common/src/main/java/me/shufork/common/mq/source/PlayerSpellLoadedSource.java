package me.shufork.common.mq.source;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PlayerSpellLoadedSource {
    String OUTPUT = "coc_player_spell_loaded_output";

    @Output(OUTPUT)
    MessageChannel output();
}