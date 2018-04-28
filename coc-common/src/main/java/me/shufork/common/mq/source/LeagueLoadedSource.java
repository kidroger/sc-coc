package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LeagueLoadedSource {
    String OUTPUT = "coc_league_Loaded_output";

    @Output(OUTPUT)
    MessageChannel output();
}
