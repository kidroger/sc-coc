package me.shufork.common.mq.source;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PlayerAchievementLoadedSource {

    String OUTPUT = "coc_player_achievement_loaded_output";

    @Output(OUTPUT)
    MessageChannel output();
}