package me.shufork.common.mq.sink;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PlayerAchievementLoadedSink{

    String INPUT = "coc_player_achievement_loaded_input";

    @Input(INPUT)
    SubscribableChannel input();
}